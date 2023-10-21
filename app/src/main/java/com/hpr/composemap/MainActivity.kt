package com.hpr.composemap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.hpr.composemap.navigation.NavigationHost
import com.hpr.composemap.ui.theme.ComposeMapTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContentHandler()
        }
    }
}

@Composable
fun ContentHandler(){
    val navController = rememberNavController()
    NavigationHost(navController = navController)
}
