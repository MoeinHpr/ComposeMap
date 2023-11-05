package com.hpr.composemap.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hpr.map.ui.MapFragment

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavRoutes.MapFragment.route) {
        composable(NavRoutes.MapFragment.route) {
            MapFragment(navController = navController)
        }
    }
}