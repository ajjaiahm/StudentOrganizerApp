# Student Organizer App - Level 2 Assignment

A modern, multi-screen Android application built strictly with **Jetpack Compose**, **Kotlin**, and **Jetpack Navigation**.

## 📌 Project Overview
- **Course**: Modern Android App Development with Kotlin
- **Focus Area**: Jetpack Compose UI, Custom Composables, State Hoisting & Jetpack Navigation
- **Architecture**: Single Activity (`MainActivity`), Declarative Navigation Graph (`NavHost` / `NavHostController`), Material 3 Design System.

---

## 🚀 Screens (5 Required Screens)

### 1. Dashboard Screen (`dashboard`)
- **Greeting Header**: Personalized welcome banner (`Welcome back, [Student Name]!`).
- **Quick Stats Row**: Elevated statistic cards showing active enrolled courses and pending tasks count in real time.
- **Active Courses Section**: Horizontally scrolling `LazyRow` featuring `CourseInfo` cards with progress indicator bars.
- **Urgent Deadlines Section**: Priority-sorted upcoming deadlines showcasing top 2 pending assignments.
- **Floating Action Button (FAB)**: Navigates directly to the **Add Assignment Screen** (`add_assignment`).

### 2. Assignments List Screen (`assignments`)
- **Top App Bar**: Dynamic `AppBar` with `My Assignments` title.
- **Status Filter Row**: Interactive RadioButtons (`All`, `Pending`, `Completed`) dynamically filtering tasks.
- **Main List**: `LazyColumn` containing interactive `AssignmentCard` components with live completion checkboxes.
- **Floating Action Button (FAB)**: Anchored bottom-right button navigating to `add_assignment`.

### 3. Profile Screen (`profile`)
- **Top App Bar**: `Student Profile` title.
- **Profile Header**: Custom `StudentProfile` composable displaying avatar, student name, USN/ID, and department.
- **Academic Performance Card**: Displays Cumulative GPA (`3.82`), Completed Credits (`84 cr`), and Current Semester (`Fall 2026`).
- **Quick Actions**: Action buttons for *Edit Profile* and *Export Summary*.

### 4. Settings Screen (`settings`)
- **Top App Bar**: `Settings` title.
- **Preferences List**:
  - **Dark Mode Preference**: Toggle `Switch` dynamically switching between Light and Dark themes.
  - **Push Notifications**: Checkbox toggle for deadline alerts.
  - **Default Start Screen**: Radio button group selecting between `Dashboard` and `Assignments`.

### 5. Add Assignment Screen (`add_assignment`)
- **Top App Bar**: `Add New Assignment` with back navigation arrow.
- **Form Fields (State-Hoisted)**:
  1. Title (`OutlinedTextField`, singleLine, required).
  2. Course selector with dropdown menu (`CS101`, `MATH201`, `ENG102`, etc.).
  3. Due Date picker with trailing calendar icon.
  4. Priority level radio selector (`Low`, `Medium`, `High`).
  5. Multi-line description notes (`OutlinedTextField`, minLines=3).
  6. Reminder toggle checkbox.
- **Validation**: "Save Assignment" button is enabled only when required fields (Title, Course, Due Date) are filled.

---

## 🧩 Custom Composables & Parameter Contracts

| Composable | Key Parameters | Purpose |
| :--- | :--- | :--- |
| **`AppBar`** | `title: String`, `canNavigateBack: Boolean`, `onBackClick: () -> Unit` | Reusable Top App Bar with back navigation |
| **`BottomBar`** | `navController: NavHostController`, `currentRoute: String?` | Central 4-tab bottom navigation bar |
| **`CourseInfo`** | `courseName: String`, `courseCode: String`, `instructor: String`, `progress: Float` | Card showing active course progress |
| **`AssignmentCard`** | `title: String`, `course: String`, `dueDate: String`, `priority: String`, `isCompleted: Boolean`, `onToggleComplete: () -> Unit` | Interactive assignment card with priority badge |
| **`StudentProfile`** | `studentName: String`, `studentId: String`, `major: String`, `avatarRes: Int` | Profile header card |
| **`AssignmentForm`** | `title`, `course`, `dueDate`, `priority`, `description`, `hasReminder`, `on*Change`, `onSave`, `onCancel`, `isSaveEnabled` | State-hoisted input form component |

---

## 🛠️ Build & Run Instructions

```bash
# Clone the repository
git clone <repo-url>
cd StudentOrganizerApp

# Build debug APK
./gradlew assembleDebug

# Install on connected device or emulator
./gradlew installDebug
```