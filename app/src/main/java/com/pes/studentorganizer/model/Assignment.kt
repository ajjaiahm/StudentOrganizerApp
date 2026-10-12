package com.pes.studentorganizer.model

data class Assignment(
    val id: String,
    val title: String,
    val course: String,
    val dueDate: String,
    val priority: String, // "Low", "Medium", "High"
    val isCompleted: Boolean = false,
    val description: String = "",
    val hasReminder: Boolean = false
)
