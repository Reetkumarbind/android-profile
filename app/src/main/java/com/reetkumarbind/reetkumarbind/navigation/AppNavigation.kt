package com.reetkumarbind.reetkumarbind.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.reetkumarbind.reetkumarbind.screens.about.AboutScreen
import com.reetkumarbind.reetkumarbind.screens.contact.ContactScreen
import com.reetkumarbind.reetkumarbind.screens.experience.ExperienceScreen
import com.reetkumarbind.reetkumarbind.screens.home.HomeScreen
import com.reetkumarbind.reetkumarbind.screens.projects.ProjectsScreen
import com.reetkumarbind.reetkumarbind.ui.components.SharedGradientBackground

/**
 * Main navigation component for the portfolio app with enhanced animations
 */
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Background gradient that persists during navigation transitions
        SharedGradientBackground()
        
        Scaffold(
            containerColor = Color.Transparent
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = NavDestination.Home.route,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(bottom = 88.dp), // Space for floating bottom nav
            enterTransition = { 
                fadeIn(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                ) + scaleIn(
                    initialScale = 0.95f,
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                )
            },
            exitTransition = {
                fadeOut(
                    animationSpec = tween(
                        durationMillis = 200,
                        easing = FastOutLinearInEasing
                    )
                ) + scaleOut(
                    targetScale = 1.05f,
                    animationSpec = tween(
                        durationMillis = 200,
                        easing = FastOutLinearInEasing
                    )
                )
            },
            popEnterTransition = {
                fadeIn(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                ) + scaleIn(
                    initialScale = 0.95f,
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                )
            },
            popExitTransition = {
                fadeOut(
                    animationSpec = tween(
                        durationMillis = 200,
                        easing = FastOutLinearInEasing
                    )
                ) + scaleOut(
                    targetScale = 1.05f,
                    animationSpec = tween(
                        durationMillis = 200,
                        easing = FastOutLinearInEasing
                    )
                )
            }
        ) {
            composable(NavDestination.Home.route) {
                HomeScreen(navController = navController)
            }
            
            composable(NavDestination.About.route) {
                AboutScreen()
            }
            
            composable(NavDestination.Projects.route) {
                ProjectsScreen()
            }
            
            composable(NavDestination.Experience.route) {
                ExperienceScreen()
            }
            
                composable(NavDestination.Contact.route) {
                    ContactScreen()
                }
            }
        }
        
        // Floating bottom navigation
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        ) {
            EnhancedBottomNavBar(
                navController = navController,
                currentRoute = currentRoute
            )
        }
    }
}

/**
 * Modern pill-shaped bottom navigation bar with gradient active state
 */
@Composable
fun EnhancedBottomNavBar(
    navController: NavHostController,
    currentRoute: String?
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            ) {
                bottomNavItems.forEach { item ->
                    val isSelected = currentRoute == item.destination.route
                    
                    PillTabItem(
                        icon = item.icon,
                        isSelected = isSelected,
                        onClick = {
                            if (currentRoute != item.destination.route) {
                                navController.navigate(item.destination.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}

/**
 * Individual pill-shaped tab item with gradient background for active state
 */
@Composable
fun PillTabItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val animatedScale by animateFloatAsState(
        targetValue = if (isSelected) 1.1f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "scale"
    )
    
    val animatedAlpha by animateFloatAsState(
        targetValue = if (isSelected) 1f else 0.6f,
        animationSpec = tween(300),
        label = "alpha"
    )
    
    Box(
        modifier = Modifier
            .size(48.dp)
            .scale(animatedScale)
            .clip(CircleShape)
            .background(
                if (isSelected) {
                    androidx.compose.ui.graphics.Brush.radialGradient(
                        colors = listOf(
                            Color(0xFF8E24AA), // Purple
                            Color(0xFF7B1FA2)  // Darker purple
                        )
                    )
                } else {
                    androidx.compose.ui.graphics.Brush.radialGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Transparent
                        )
                    )
                }
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onClick() },
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = animatedAlpha)
        )
    }
}