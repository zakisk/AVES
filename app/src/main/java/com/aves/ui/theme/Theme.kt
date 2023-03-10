package com.aves.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun AVESTheme(
    darkTheme: Boolean = false,
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
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = colorScheme.primary.toArgb()
            ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = darkTheme
        }
    }


    val typography = Typography.copy(
        displayLarge = MaterialTheme.typography.displayLarge.copy(fontFamily = hitfitFonts),
        displayMedium = MaterialTheme.typography.displayMedium.copy(fontFamily = hitfitFonts),
        displaySmall = MaterialTheme.typography.displaySmall.copy(fontFamily = hitfitFonts),
        headlineLarge = MaterialTheme.typography.headlineMedium.copy(fontFamily = hitfitFonts),
        headlineMedium = MaterialTheme.typography.headlineMedium.copy(fontFamily = hitfitFonts),
        headlineSmall = MaterialTheme.typography.headlineSmall.copy(fontFamily = hitfitFonts),
        titleLarge = MaterialTheme.typography.titleLarge.copy(fontFamily = hitfitFonts),
        titleMedium = MaterialTheme.typography.titleMedium.copy(fontFamily = hitfitFonts),
        titleSmall = MaterialTheme.typography.titleSmall.copy(fontFamily = hitfitFonts),
        bodyLarge = MaterialTheme.typography.bodyLarge.copy(fontFamily = hitfitFonts),
        bodyMedium = MaterialTheme.typography.bodyMedium.copy(fontFamily = hitfitFonts),
        bodySmall = MaterialTheme.typography.bodySmall.copy(fontFamily = hitfitFonts),
        labelLarge = MaterialTheme.typography.labelLarge.copy(fontFamily = hitfitFonts),
        labelMedium = MaterialTheme.typography.labelMedium.copy(fontFamily = hitfitFonts),
        labelSmall = MaterialTheme.typography.labelSmall.copy(fontFamily = hitfitFonts)
    )



    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography,
        content = content
    )
}