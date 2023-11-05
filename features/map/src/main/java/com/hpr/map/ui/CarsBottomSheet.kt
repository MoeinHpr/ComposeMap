package com.hpr.map.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.hpr.data.model.CarsModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContainerBottomSheet(modifier: Modifier = Modifier, carsList: List<CarsModel>) {
    BottomSheetScaffold(sheetContent = {
        LazyColumn(modifier = modifier.fillMaxSize()) {
            items(carsList) {
                CarsCardItem(carsModel = it)
            }
        }
    }) {}

}

@Composable
fun CarsCardItem(carsModel: CarsModel) {
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
