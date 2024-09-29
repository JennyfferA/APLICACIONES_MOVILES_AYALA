package com.example.coches.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Blue40,
    secondary = LightGreen40,
    tertiary = Orange40
)

private val LightColorScheme = lightColorScheme(
    primary = Blue80,
    secondary = LightGreen80,
    tertiary = Orange80
)

@Composable
fun CochesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // El color dinámico está disponible en Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        // Si los colores dinámicos están activados y el dispositivo soporta Android 12+
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        // De lo contrario, utiliza la paleta personalizada
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    // Aplica el tema a la aplicación
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography, // Usa tu tipografía personalizada si la has definido
        content = content
    )
}
