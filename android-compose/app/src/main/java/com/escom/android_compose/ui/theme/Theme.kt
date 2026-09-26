package com.escom.android_compose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

// Esquema de colores NFL para modo claro y oscuro. No se usa "dynamicColor" del
// sistema para que la identidad visual del catálogo se mantenga siempre.
private val DarkColorScheme = darkColorScheme(
    primary = VerdeCampoClaro,
    onPrimary = NegroArbitro,
    secondary = BlancoLineas,
    onSecondary = NegroArbitro,
    error = AmarilloPenalti,
    onError = NegroArbitro,
    background = NegroArbitro,
    onBackground = BlancoLineas,
    surface = NegroArbitro,
    onSurface = BlancoLineas
)

private val LightColorScheme = lightColorScheme(
    primary = VerdeCampo,
    onPrimary = BlancoLineas,
    secondary = NegroArbitro,
    onSecondary = BlancoLineas,
    error = AmarilloPenalti,
    onError = NegroArbitro,
    background = BlancoLineas,
    onBackground = NegroArbitro,
    surface = BlancoLineas,
    onSurface = NegroArbitro
)

@Composable
fun AndroidcomposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
