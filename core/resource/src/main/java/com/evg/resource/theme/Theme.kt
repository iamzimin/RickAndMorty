package com.evg.resource.theme

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
    primary = primaryDark,
    secondary = secondaryDark,
    onSecondary = Color(0xFF154121),
    //tertiary = Color(0xFF0C6370),
    surfaceVariant = Color(0xFF364139),
    secondaryContainer = primaryDark,
    //outline = Color(0xFFFFFFFF)

    /*primaryContainer = Color.Red,
    onPrimaryContainer = Color.Red,
    inversePrimary = Color.Red,
    onSecondary = Color.Red,
    onSecondaryContainer = Color.Red,
    tertiary = Color.Red,
    onTertiary = Color.Red,
    tertiaryContainer = Color.Red,
    onTertiaryContainer = Color.Red,
    background = Color.Red,
    onBackground = Color.Red,

    surface = Color.Black,
    onSurface = Color.White,
    onSurfaceVariant = Color.White,
    surfaceTint = Color.White,

    inverseSurface = Color.Red,
    inverseOnSurface = Color.Red,
    error = Color.Red,
    onError = Color.Red,
    errorContainer = Color.Red,
    onErrorContainer = Color.Red,
    outline = Color.Red,
    outlineVariant = Color.Red,
    scrim = Color.Red,
    surfaceBright = Color.Red,
    surfaceContainer = Color.Red,
    surfaceContainerHigh = Color.Red,
    surfaceContainerHighest = Color.Red,
    surfaceContainerLow = Color.Red,
    surfaceContainerLowest = Color.Red,
    surfaceDim = Color.Red,*/
)

private val LightColorScheme = lightColorScheme(
    primary = primaryLight,
    secondary = secondaryLight,
    onSecondary = secondaryLight,
    //tertiary = Color(0xFF0C6370),
    surfaceVariant = Color(0xFFD1E9D8),
    secondaryContainer = primaryLight,
    //outline = Color(0xFF000000)
)

@Composable
fun RickAndMortyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
      /*dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
        val context = LocalContext.current
        if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }*/
      darkTheme -> DarkColorScheme
      else -> LightColorScheme
    }

    MaterialTheme(
      colorScheme = colorScheme,
      typography = Typography,
      content = content
    )
}