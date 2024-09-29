package com.example.coches.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.coches.ui.viewmodel.CocheViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntradaCocheScreen(viewModel: CocheViewModel, navController: NavController) {
    var marca by remember { mutableStateOf("") }
    var modelo by remember { mutableStateOf("") }
    var ano by remember { mutableStateOf("") }
    var kilometraje by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Brush.verticalGradient(listOf(Color.LightGray, Color.White))),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                text = "REGISTRO DE COCHES",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.padding(bottom = 20.dp)
            )
        }

        // Campos para ingresar la información del coche
        item {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Column(modifier = Modifier.weight(1f).padding(end = 8.dp)) {
                    OutlinedTextField(
                        value = marca,
                        onValueChange = { marca = it },
                        label = { Text("Marca") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF6200EE),
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = ano,
                        onValueChange = { ano = it },
                        label = { Text("Año") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth(),
                    )
                }

                Column(modifier = Modifier.weight(1f).padding(start = 8.dp)) {
                    OutlinedTextField(
                        value = modelo,
                        onValueChange = { modelo = it },
                        label = { Text("Modelo") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = kilometraje,
                        onValueChange = { kilometraje = it },
                        label = { Text("Kilometraje") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }
        }

        // Botón para registrar coche
        item {
            Button(
                onClick = {
                    showError = false
                    val anoInt = ano.toIntOrNull()
                    val kilometrajeInt = kilometraje.toIntOrNull()

                    if (marca.isNotBlank() && modelo.isNotBlank() && anoInt != null && kilometrajeInt != null) {
                        // Inserta el coche en el ViewModel
                        viewModel.insertCoche(marca, modelo, anoInt, kilometrajeInt)
                        // Navegar a la pantalla de registros de coches
                        navController.navigate("listScreen")
                    } else {
                        showError = true
                        Toast.makeText(
                            context,
                            "Por favor, complete todos los campos correctamente",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF03DAC5))
            ) {
                Text("Registrar Coche", color = Color.White)
            }
        }

        // Botón adicional para navegar a la lista de registros de coches
        item {
            TextButton(
                onClick = {
                    // Navega directamente a la pantalla de lista de coches
                    navController.navigate("listScreen")
                },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text(
                    "Ver Lista de Coches Registrados",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}


