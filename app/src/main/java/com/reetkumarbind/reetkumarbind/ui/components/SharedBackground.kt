package com.reetkumarbind.reetkumarbind.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.reetkumarbind.reetkumarbind.ui.theme.*

/**
 * Shared gradient background component used across all screens
 */
@Composable
fun SharedGradientBackground() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .offset(y = (-100).dp)
            .height(2000.dp)
            .background(
                brush = androidx.compose.ui.graphics.Brush.verticalGradient(
                    colors = listOf(
                        GradientStart.copy(alpha = 0.9f),
                        GradientStart.copy(alpha = 0.8f),
                        GradientMiddle.copy(alpha = 0.7f),
                        SurfaceTintBlue.copy(alpha = 0.6f),
                        SurfaceTintCyan.copy(alpha = 0.5f),
                        SurfaceTintPink.copy(alpha = 0.4f),
                        SurfaceTintPurple.copy(alpha = 0.5f),
                        GradientEnd.copy(alpha = 0.3f),
                        SurfaceTintBlue.copy(alpha = 0.2f),
                        Color.Transparent
                    )
                )
            )
    )
}

/**
 * Wrapper composable that provides the shared background for any screen content
 * Now just passes through content since background is handled at navigation level
 */
@Composable
fun ScreenWithBackground(
    content: @Composable () -> Unit
) {
    content()
}