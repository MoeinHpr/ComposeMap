package com.hpr.composemap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.hpr.composemap.navigation.NavigationHost
import com.hpr.data.base.BaseViewModel
import com.hpr.map.domain.MapViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mapViewModel : MapViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ContentHandler(
                arrayOf(mapViewModel)
            )
        }
    }
}

@Composable
fun ContentHandler(viewModelList : Array<BaseViewModel>){
    val navController = rememberNavController()
    NavigationHost(navController = navController , viewModels = viewModelList)
}
