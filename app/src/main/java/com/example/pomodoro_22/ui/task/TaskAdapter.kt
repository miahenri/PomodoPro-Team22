package com.example.pomodoro_22.ui.task

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pomodoro_22.model.Task

@Composable
fun TaskItem(
    task: Task,
    onTaskClick: (Task) -> Unit,
    onTaskDelete: (Task) -> Unit  // Added delete functionality
) {
    val checkedState = remember { mutableStateOf(task.isCompleted) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Checkbox(
            checked = checkedState.value,
            onCheckedChange = {
                checkedState.value = it
                onTaskClick(task.copy(isCompleted = it))
            }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(task.name)
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = { onTaskDelete(task) }) {
            Text("Delete")
        }
    }
}

@Composable
fun TaskList(
    tasks: List<Task>,
    onTaskClick: (Task) -> Unit,
    onTaskDelete: (Task) -> Unit  // Added delete functionality
) {
    Column {
        tasks.forEach { task ->
            TaskItem(
                task = task,
                onTaskClick = onTaskClick,
                onTaskDelete = onTaskDelete
            )
        }
    }
}
