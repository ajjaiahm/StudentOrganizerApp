package com.pes.studentorganizer.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.pes.studentorganizer.components.AppBar
import com.pes.studentorganizer.components.AssignmentForm
import com.pes.studentorganizer.model.Assignment
import java.util.UUID

@Composable
fun AddAssignmentScreen(
    onSaveAssignment: (Assignment) -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Form state managed via remember and mutableStateOf
    var title by remember { mutableStateOf("") }
    var course by remember { mutableStateOf("") }
    var dueDate by remember { mutableStateOf("") }
    var priority by remember { mutableStateOf("Medium") }
    var description by remember { mutableStateOf("") }
    var hasReminder by remember { mutableStateOf(false) }

    // Validation: Save button disabled until Title, Course, Due Date are populated
    val isSaveEnabled by remember {
        derivedStateOf {
            title.trim().isNotEmpty() &&
            course.trim().isNotEmpty() &&
            dueDate.trim().isNotEmpty()
        }
    }

    Scaffold(
        topBar = {
            AppBar(
                title = "Add New Assignment",
                canNavigateBack = true,
                onBackClick = onNavigateBack
            )
        },
        modifier = modifier
    ) { innerPadding ->
        AssignmentForm(
            title = title,
            course = course,
            dueDate = dueDate,
            priority = priority,
            description = description,
            hasReminder = hasReminder,
            onTitleChange = { title = it },
            onCourseChange = { course = it },
            onDueDateChange = { dueDate = it },
            onPriorityChange = { priority = it },
            onDescriptionChange = { description = it },
            onReminderChange = { hasReminder = it },
            onSave = {
                if (isSaveEnabled) {
                    val newAssignment = Assignment(
                        id = UUID.randomUUID().toString(),
                        title = title.trim(),
                        course = course.trim(),
                        dueDate = dueDate.trim(),
                        priority = priority,
                        isCompleted = false,
                        description = description.trim(),
                        hasReminder = hasReminder
                    )
                    onSaveAssignment(newAssignment)
                    onNavigateBack()
                }
            },
            onCancel = onNavigateBack,
            isSaveEnabled = isSaveEnabled,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        )
    }
}
