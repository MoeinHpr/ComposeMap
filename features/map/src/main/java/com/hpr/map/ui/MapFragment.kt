@file:Suppress("UNUSED_PARAMETER")

package com.hpr.map.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.bottomsheet.BottomSheetDragHandleView
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.hpr.core.utils.hasNullValue
import com.hpr.core.utils.loadImageBitmap
import com.hpr.data.model.CarsModel
import com.hpr.map.domain.MapViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.reflect.KProperty


@AndroidEntryPoint
class MapFragment : FragmentActivity() {
    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return super.onCreateView(name, context, attrs)
    }
}

@Composable
fun MapFragment(navController: NavController, viewModel: MapViewModel) {
    ContainerUi(viewModel = viewModel)
}

@Composable
fun ContainerUi(modifier: Modifier = Modifier, viewModel: MapViewModel) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        ContainerMap(modifier, viewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContainerBottomSheet(modifier: Modifier = Modifier, carsList: List<CarsModel>) {
    BottomSheetScaffold(sheetContent = {
        LazyColumn(modifier = modifier.fillMaxSize()) {
            items(carsList) {
                PlantCard(carsModel = it)
            }
        }
    }) {}

}

@Composable
fun PlantCard(carsModel: CarsModel) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = ShapeDefaults.Medium,
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 5.dp),
        colors = CardDefaults.elevatedCardColors(containerColor = Color.Gray)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model = carsModel.carImageUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(130.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Fit,
            )
            Column(Modifier.padding(8.dp)) {
                Text(
                    text = carsModel.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black,
                )
                Text(
                    text = carsModel.color,
                    style = MaterialTheme.typography.titleSmall,
                )
            }
        }
    }
}

@Composable
fun ContainerMap(modifier: Modifier, viewModel: MapViewModel?) {

    var carsList by remember {
        mutableStateOf(listOf<CarsModel>())
    }

    val uiSettings by remember {
        mutableStateOf(MapUiSettings(zoomControlsEnabled = false))
    }
    val properties by remember {
        mutableStateOf(MapProperties())
    }
    var showProgress by remember {
        mutableStateOf(true)
    }


    val context = LocalContext.current


    carsList = viewModel?.carsListFlow()?.collectAsState(initial = listOf())?.value ?: listOf()
    val mCarsList by remember {
        mutableStateOf(mutableListOf<CarsModel>())
    }


    if (carsList.isNotEmpty() && carsList.map { it.carImageBitmap }.hasNullValue()) {
        LaunchedEffect(key1 = true, block = {
            launch {
                carsList.forEach { carsModel ->
                    context.loadImageBitmap(carsModel.id, carsModel.carImageUrl) { bitmap, id ->
                        mCarsList.add(
                            carsList.first { it.id == id }.copy(carImageBitmap = bitmap)
                        )
                        //progress += progress + 20
                    }
                }
                //carsList = mCarsList
                showProgress = false
            }
        })
    }


    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    )
    {

        GoogleMap(
            modifier = modifier.fillMaxSize(),
            uiSettings = uiSettings,
            properties = properties,
            cameraPositionState = getCameraState(carsList),
            content = {
                mCarsList.forEach { car ->
                    car.carImageBitmap?.let {
                        Marker(
                            state = MarkerState(
                                LatLng(car.latitude, car.longitude)
                            ),
                            icon = BitmapDescriptorFactory.fromBitmap(it)
                        )
                    }
                }
            })


        if (showProgress) {
            CircularProgressIndicator(
                modifier = Modifier.width(64.dp),
                color = Color.Blue,
                trackColor = Color.Cyan
            )
        }

        ContainerBottomSheet(carsList = carsList)
    }

}


fun getCameraState(carsList: List<CarsModel>): CameraPositionState {
    return if (carsList.isNotEmpty()) {
        CameraPositionState(
            CameraPosition(
                LatLng(carsList.first().latitude, carsList.first().longitude),
                6f,
                2f,
                2f
            )
        )
    } else CameraPositionState()
}


