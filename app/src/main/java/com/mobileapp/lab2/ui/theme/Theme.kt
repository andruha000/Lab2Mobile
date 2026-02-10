package com.mobileapp.lab2.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = Green,
    onPrimary = White,
    secondary = DarkGray2,
    background = DarkGray,
    onSurface = White,
    surfaceContainer = DarkGray2,
    surfaceVariant = LightGreen
)

private val LightColorScheme = lightColorScheme(
    primary = Green,
    onPrimary = White,
    secondary = Gray,
    background = LightGray,
    onSurface = Black,
    surfaceContainer = White,
    surfaceVariant = LightGreen
)

@Composable
fun Lab2Theme(
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