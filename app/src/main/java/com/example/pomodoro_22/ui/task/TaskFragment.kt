package com.example.pomodoro_22.ui.task

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.pomodoro_22.model.Task

@Composable
fun TaskFragment(navController: NavHostController, taskViewModel: TaskViewModel) {
    var taskName by remember { mutableStateOf("") }

    // Observe tasks from ViewModel
    val tasks by taskViewModel.tasks.observeAsState(emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Input field for adding a new task
        TextField(
            value = taskName,
            onValueChange = { taskName = it },
            label = { Text("Task Name") },
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Display the list of tasks
        TaskList(
            tasks = tasks,
            onTaskClick = { task -> taskViewModel.updateTask(task) },
            onTaskDelete = { task -> taskViewModel.deleteTask(task) }
        )

        // Button to add a new task
        Button(
            onClick = {
                if (taskName.isNotEmpty()) {
                    taskViewModel.addTask(Task(name = taskName))
                    taskName = "" // Clear the input after adding
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Add Task")
        }

        // Button to go back (example navigation logic)
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Go Back")
        }
    }
}
