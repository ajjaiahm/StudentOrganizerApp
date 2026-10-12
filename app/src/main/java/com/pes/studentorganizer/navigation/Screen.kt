package com.pes.studentorganizer.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String = "", val icon: ImageVector? = null) {
    object Dashboard : Screen("dashboard", "Dashboard", Icons.Default.Dashboard)
    object Assignments : Screen("assignments", "Assignments", Icons.AutoMirrored.Filled.Assignment)
    object Profile : Screen("profile", "Profile", Icons.Default.Person)
    object Settings : Screen("settings", "Settings", Icons.Default.Settings)
    object AddAssignment : Screen("add_assignment", "Add Assignment", null)

    companion object {
        val bottomNavItems = listOf(
            Dashboard,
            Assignments,
            Profile,
            Settings
        )
    }
}
