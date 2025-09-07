package com.reetkumarbind.reetkumarbind.navigation

/**
 * Navigation destinations for the portfolio app
 */
sealed class NavDestination(val route: String) {
    object Home : NavDestination("home")
    object About : NavDestination("about")
    object Projects : NavDestination("projects")
    object Experience : NavDestination("experience")
    object Contact : NavDestination("contact")
    
    companion object {
        val bottomNavItems = listOf(Home, About, Projects, Experience, Contact)
    }
}