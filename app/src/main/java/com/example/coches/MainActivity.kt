package com.example.coches

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.coches.data.AppDataContainer
import com.example.coches.navigation.AppNavigation
import com.example.coches.ui.screens.EntradaCocheScreen
import com.example.coches.ui.screens.RegistroCocheScreen
import com.example.coches.ui.theme.CochesTheme
import com.example.coches.ui.viewmodel.CocheViewModel
import com.example.coches.ui.viewmodel.CocheViewModelFactory


class MainActivity : ComponentActivity() {
    private lateinit var viewModel: CocheViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa tu AppContainer o repositorio aquí
        val appContainer = AppDataContainer(context = this)
        val repository = appContainer.cocheRepository

        // Crea la instancia del ViewModel con el Factory
        val viewModelFactory = CocheViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CocheViewModel::class.java)

        setContent {
            // Asegúrate de aplicar tu tema personalizado
            CochesTheme {
                AppNavigation(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: CocheViewModel) {
    // Crea un NavController para la navegación
    val navController = rememberNavController()

    // Usamos un Scaffold para proporcionar la estructura básica de la UI
    Scaffold { paddingValues ->
        // Configuramos el NavHost
        NavHost(
            navController = navController,
            startDestination = "inputScreen",
            Modifier.padding(paddingValues)
        ) {
            composable("inputScreen") {
                EntradaCocheScreen(viewModel = viewModel, navController = navController)
            }
            composable("listScreen") {
                RegistroCocheScreen(viewModel = viewModel, navController = navController)
            }
        }
    }
}
