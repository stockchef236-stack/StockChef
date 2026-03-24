package com.stockchef.app.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryDark,
    secondary = SecondaryDark,
    tertiary = TertiaryDark,

    background = BackgroundDark,
    surface = SurfaceDark,

    onPrimary = OnPrimaryDark,
    onSecondary = OnPrimaryDark,
    onTertiary = OnPrimaryDark,

    onBackground = OnBackgroundDark,
    onSurface = OnBackgroundDark
)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryBlue,
    secondary = SecondaryBlue,
    tertiary = TertiaryBlue,

    background = BackgroundLight,
    surface = SurfaceLight,

    onPrimary = OnPrimaryLight,
    onSecondary = OnPrimaryLight,
    onTertiary = OnPrimaryLight,

    onBackground = OnBackgroundLight,
    onSurface = OnBackgroundLight
)

@Composable
fun StockChefTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}