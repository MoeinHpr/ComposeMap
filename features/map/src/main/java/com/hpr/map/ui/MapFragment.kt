@file:Suppress("UNUSED_PARAMETER")

package com.hpr.map.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
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
import kotlinx.coroutines.launch


@Composable
fun MapFragment(
    navController: NavController,
    viewModel: MapViewModel = hiltViewModel(),
) {
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

@Composable
fun ContainerMap(modifier: Modifier, viewModel: MapViewModel) {

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
    var updatedCarsList by remember {
        mutableStateOf(emptyList<CarsModel>())
    }

    val context = LocalContext.current

    carsList = viewModel.carsListFlow().collectAsState(initial = listOf()).value

    val mCarsList = mutableListOf<CarsModel>()

    if (carsList.isNotEmpty() && carsList.map { it.carImageBitmap }.hasNullValue()) {
        LaunchedEffect(key1 = true, block = {
            launch {
                carsList.forEach { carsModel ->
                    context.loadImageBitmap(carsModel.id, carsModel.carImageUrl) { bitmap, id ->
                        mCarsList.add(
                            carsList.first { it.id == id }.copy(carImageBitmap = bitmap)
                        )
                    }
                }
                updatedCarsList = mCarsList
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
                updatedCarsList.forEach { car ->
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


