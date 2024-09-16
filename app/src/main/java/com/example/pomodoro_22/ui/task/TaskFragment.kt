package com.example.pomodoro_22.ui.task

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.pomodoro_22.R
import com.example.pomodoro_22.model.Task
import com.example.pomodoro_22.ui.main.DividerLine
import com.example.pomodoro_22.ui.main.PomodoroTitle
import com.example.pomodoro_22.ui.main.RoundedIconButton
import com.example.pomodoro_22.viewmodel.TaskViewModel

@Composable
fun TaskFragment(navController: NavHostController, taskViewModel: TaskViewModel) {
    var taskName by remember { mutableStateOf("") }

    // Observe tasks from ViewModel
    val tasks by taskViewModel.tasks.observeAsState(emptyList())

    // Separate tasks into completed and incomplete
    val completedTasks = tasks.filter { it.isCompleted }
    val incompleteTasks = tasks.filter { !it.isCompleted }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
            .padding(top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top Row: Back button, title, and placeholder for spacing
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,  // Ensure elements are spaced
            verticalAlignment = Alignment.CenterVertically
        ) {
            RoundedIconButton(
                onClick = {
                    Log.d("TasksScreen", "Back button clicked")
                    navController.popBackStack() // Navigate back to the previous screen
                },
                icon = R.drawable.arrowbackicon,
                contentDescription = "Go back"
            )

            // Center the title between the buttons
            PomodoroTitle(name = "Tasks")

            Spacer(modifier = Modifier.size(56.dp))  // Placeholder to balance space
        }

        DividerLine()
        Spacer(modifier = Modifier.height(16.dp))

        // Row to align Task Input Field and Add Task Button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Task Input Field
            TextField(
                value = taskName,
                onValueChange = { taskName = it },
                label = { Text("Add task here...") },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )

            // Rounded Icon Button to Add Task
            RoundedIconButton(
                onClick = {
                    if (taskName.isNotEmpty()) {
                        taskViewModel.addTask(Task(name = taskName))
                        taskName = "" // Clear the input after adding
                    }
                },
                icon = R.drawable.addicon,  // Assuming you have an ic_add resource with a "+" icon
                contentDescription = "Add Task"
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Scrollable list of incomplete tasks
        TaskList(
            tasks = incompleteTasks,
            onTaskClick = { task ->
                // Update task status to completed
                taskViewModel.updateTask(task.copy(isCompleted = true))
            },
            onTaskDelete = { task -> taskViewModel.deleteTask(task) },
            modifier = Modifier
                .weight(1f) // Allow it to take up available space with scroll
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Row for "Erledigt" title and delete button for completed tasks
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,  // Ensure elements are spaced
            verticalAlignment = Alignment.CenterVertically
        ) {
            RoundedIconButton(
                onClick = {
                    Log.d("TasksScreen", "Delete all completed tasks clicked")
                    completedTasks.forEach { task ->
                        taskViewModel.deleteTask(task)
                    }
                },
                icon = R.drawable.deleteicon,
                contentDescription = "Delete completed tasks"
            )

            // Center the title between the buttons
            PomodoroTitle(name = "Erledigt")

            Spacer(modifier = Modifier.size(56.dp))  // Placeholder to balance space
        }

        DividerLine()
        Spacer(modifier = Modifier.height(16.dp))

        // Scrollable list of completed tasks
        TaskList(
            tasks = completedTasks,
            onTaskClick = { task ->
                // Update task status to incomplete (move back to upper list)
                taskViewModel.updateTask(task.copy(isCompleted = false))
            },
            onTaskDelete = { task -> taskViewModel.deleteTask(task) },
            modifier = Modifier
                .weight(1f) // Allow it to take up available space with scroll
        )
    }
}