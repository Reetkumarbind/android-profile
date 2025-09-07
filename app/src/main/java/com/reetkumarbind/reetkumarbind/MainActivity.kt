package com.reetkumarbind.reetkumarbind

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.reetkumarbind.reetkumarbind.navigation.AppNavigation
import com.reetkumarbind.reetkumarbind.ui.theme.ReetKumarBindTheme

/**
 * Main activity for the Reet Kumar Bind portfolio app
 * 
 * This activity serves as the entry point for the application and sets up
 * the main theme, navigation, and system UI configuration.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Enable edge-to-edge display
        enableEdgeToEdge()
        
        // Configure window for immersive experience
        WindowCompat.setDecorFitsSystemWindows(window, false)
        
        setContent {
            // Portfolio app theme with dynamic color support
            ReetKumarBindTheme {
                // System UI configuration
                val systemUiController = rememberSystemUiController()
                val useDarkIcons = !isSystemInDarkTheme()
                
                // Configure system bars with theme-aware colors
                DisposableEffect(systemUiController, useDarkIcons) {
                    systemUiController.setSystemBarsColor(
                        color = Color.Transparent,
                        darkIcons = useDarkIcons
                    )
                    
                    // Configure navigation bar for better integration
                    systemUiController.setNavigationBarColor(
                        color = Color.Transparent,
                        darkIcons = useDarkIcons
                    )
                    
                    onDispose {
                        // Clean up if needed
                    }
                }
                
                // Main navigation component with all screens
                AppNavigation()
            }
        }
    }
}