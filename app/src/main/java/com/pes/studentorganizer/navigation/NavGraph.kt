package com.pes.studentorganizer.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.pes.studentorganizer.R
import com.pes.studentorganizer.components.BottomBar
import com.pes.studentorganizer.model.Assignment
import com.pes.studentorganizer.model.Course
import com.pes.studentorganizer.model.Student
import com.pes.studentorganizer.screens.AddAssignmentScreen
import com.pes.studentorganizer.screens.AssignmentsScreen
import com.pes.studentorganizer.screens.DashboardScreen
import com.pes.studentorganizer.screens.ProfileScreen
import com.pes.studentorganizer.screens.SettingsScreen

@Composable
fun MainAppNavHost(
    isDarkMode: Boolean,
    onDarkModeToggle: (Boolean) -> Unit,
    navController: NavHostController = rememberNavController()
) {
    // Shared State Models
    val student = remember {
        Student(
            studentName = "Ajjaiah Matada",
            studentId = "PES1UG25CS823",
            major = "Computer Science & Engineering",
            avatarRes = R.drawable.ic_student_avatar,
            gpa = 3.82,
            completedCredits = 84,
            currentSemester = "Fall 2026"
        )
    }

    val courses = remember {
        listOf(
            Course("Modern Android App Dev", "CS101", "Prof. Shridevi Sawant", 0.75f),
            Course("Database Management Systems", "CS304", "Prof. Ritu Sharma", 0.60f),
            Course("Linear Algebra & Optimization", "MATH201", "Prof. K. Ramesh", 0.85f),
            Course("Technical Communication", "ENG102", "Prof. Anita Sen", 0.45f)
        )
    }

    val assignments = remember {
        mutableStateListOf(
            Assignment("1", "Compose UI & Navigation Project", "CS101", "Oct 12, 2026", "High", false, "Implement 5 screens and custom composables", true),
            Assignment("2", "SQL Normalization Exercise", "CS304", "Oct 14, 2026", "High", false, "Complete 3NF and BCNF problems", true),
            Assignment("3", "Matrix Decomposition Assignment", "MATH201", "Oct 16, 2026", "Medium", false, "SVD and Eigenvalue calculations", false),
            Assignment("4", "ISA-1 Exam Preparation", "CS101", "Oct 18, 2026", "High", false, "Review Kotlin lambdas and Compose state", true),
            Assignment("5", "Technical Resume Draft", "ENG102", "Oct 20, 2026", "Low", false, "Prepare two-page resume in PDF", false),
            Assignment("6", "Kotlin Coroutines Lab", "CS101", "Oct 05, 2026", "Medium", true, "Async flows and job cancellation", false),
            Assignment("7", "ER Diagram Submission", "CS304", "Oct 02, 2026", "Low", true, "Hospital management system schema", false)
        )
    }

    // App Preferences State
    var pushNotificationsEnabled by remember { mutableStateOf(true) }
    var defaultStartScreen by remember { mutableStateOf("Dashboard") }

    // Track current route to conditionally show BottomBar
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Conditionally hide the BottomBar when on the add_assignment screen
    val shouldShowBottomBar = currentRoute != Screen.AddAssignment.route

    val startDestination = if (defaultStartScreen == "Assignments") {
        Screen.Assignments.route
    } else {
        Screen.Dashboard.route
    }

    Scaffold(
        bottomBar = {
            if (shouldShowBottomBar) {
                BottomBar(
                    navController = navController,
                    currentRoute = currentRoute
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(innerPadding)
        ) {
            // Screen 1: Dashboard
            composable(Screen.Dashboard.route) {
                DashboardScreen(
                    student = student,
                    courses = courses,
                    assignments = assignments,
                    onToggleAssignmentComplete = { id ->
                        val index = assignments.indexOfFirst { it.id == id }
                        if (index != -1) {
                            val item = assignments[index]
                            assignments[index] = item.copy(isCompleted = !item.isCompleted)
                        }
                    },
                    onNavigateToAddAssignment = {
                        navController.navigate(Screen.AddAssignment.route)
                    }
                )
            }

            // Screen 2: Assignments
            composable(Screen.Assignments.route) {
                AssignmentsScreen(
                    assignments = assignments,
                    onToggleAssignmentComplete = { id ->
                        val index = assignments.indexOfFirst { it.id == id }
                        if (index != -1) {
                            val item = assignments[index]
                            assignments[index] = item.copy(isCompleted = !item.isCompleted)
                        }
                    },
                    onNavigateToAddAssignment = {
                        navController.navigate(Screen.AddAssignment.route)
                    }
                )
            }

            // Screen 3: Profile
            composable(Screen.Profile.route) {
                ProfileScreen(student = student)
            }

            // Screen 4: Settings
            composable(Screen.Settings.route) {
                SettingsScreen(
                    isDarkMode = isDarkMode,
                    onDarkModeToggle = onDarkModeToggle,
                    pushNotificationsEnabled = pushNotificationsEnabled,
                    onPushNotificationsToggle = { pushNotificationsEnabled = it },
                    defaultStartScreen = defaultStartScreen,
                    onDefaultStartScreenChange = { defaultStartScreen = it }
                )
            }

            // Screen 5: Add Assignment
            composable(Screen.AddAssignment.route) {
                AddAssignmentScreen(
                    onSaveAssignment = { newAssignment ->
                        assignments.add(0, newAssignment)
                    },
                    onNavigateBack = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}
