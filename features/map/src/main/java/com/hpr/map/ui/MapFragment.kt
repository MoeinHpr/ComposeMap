package com.hpr.map.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings

@Composable
fun MapFragment(navController: NavController) {
    ContainerUi()
}


@Composable
fun ContainerUi(modifier: Modifier = Modifier) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        ContainerMap(modifier)
    }
}

@Composable
fun ContainerBottomSheet() {

}

@Composable
fun ContainerMap(modifier: Modifier) {

    val uiSettings by remember {
        mutableStateOf(MapUiSettings())
    }
    val properties by remember {
        mutableStateOf(MapProperties(mapType = MapType.NORMAL))
    }
    GoogleMap(
        modifier = modifier.fillMaxSize(),
        uiSettings = uiSettings,
        properties = properties
    )
}