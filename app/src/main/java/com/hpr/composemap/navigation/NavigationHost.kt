package com.hpr.composemap.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hpr.data.base.BaseViewModel
import com.hpr.map.domain.MapViewModel
import com.hpr.map.ui.MapFragment

@Composable
fun NavigationHost(navController : NavHostController , viewModels : Array<out BaseViewModel>) {
    NavHost(navController = navController , startDestination = NavRoutes.MapFragment.route){
        composable(NavRoutes.MapFragment.route){
            MapFragment(navController = navController , viewModel = viewModels[NavRoutes.MapFragment.ordinal] as MapViewModel)
        }
    }
}