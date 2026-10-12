package com.pes.studentorganizer.model

import com.pes.studentorganizer.R

data class Student(
    val studentName: String = "Ajjaiah Matada",
    val studentId: String = "PES1UG25CS823",
    val major: String = "Computer Science & Engineering",
    val avatarRes: Int = R.drawable.ic_student_avatar,
    val gpa: Double = 3.82,
    val completedCredits: Int = 84,
    val currentSemester: String = "Fall 2026"
)
