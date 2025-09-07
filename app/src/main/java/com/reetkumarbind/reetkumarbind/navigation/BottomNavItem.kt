package com.reetkumarbind.reetkumarbind.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Work
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Work
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Data class representing a bottom navigation item
 */
data class BottomNavItem(
    val destination: NavDestination,
    val icon: ImageVector,
    val label: String
)

/**
 * List of bottom navigation items for the app
 */
val bottomNavItems = listOf(
    BottomNavItem(
        destination = NavDestination.Home,
        icon = Icons.Outlined.Home,
        label = "Home"
    ),
    BottomNavItem(
        destination = NavDestination.About,
        icon = Icons.Outlined.Person,
        label = "About"
    ),
    BottomNavItem(
        destination = NavDestination.Projects,
        icon = Icons.Outlined.Work,
        label = "Projects"
    ),
    BottomNavItem(
        destination = NavDestination.Experience,
        icon = Icons.Outlined.List,
        label = "Experience"
    ),
    BottomNavItem(
        destination = NavDestination.Contact,
        icon = Icons.Outlined.AccountCircle,
        label = "Contact"
    )
)