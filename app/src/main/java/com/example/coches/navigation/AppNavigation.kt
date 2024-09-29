package com.example.coches.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.coches.ui.screens.EntradaCocheScreen
import com.example.coches.ui.screens.RegistroCocheScreen
import com.example.coches.ui.viewmodel.CocheViewModel

@Composable
fun AppNavigation(viewModel: CocheViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "inputScreen") {
        composable("inputScreen") {
            EntradaCocheScreen(viewModel = viewModel, navController = navController)
        }
        composable("listScreen") {
            RegistroCocheScreen(viewModel = viewModel, navController = navController)
        }
    }
}