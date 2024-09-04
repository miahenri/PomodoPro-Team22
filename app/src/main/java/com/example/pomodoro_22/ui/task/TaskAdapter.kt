package com.example.pomodoro_22.ui.task

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pomodoro_22.model.Task
import com.example.pomodoro_22.ui.main.ui.theme.white

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import com.example.pomodoro_22.R

@Composable
fun TaskTitle(name: String, modifier: Modifier = Modifier) {
    Text(
        text = name,
        color = white,
        fontSize = 38.sp,
        fontWeight = FontWeight.Normal,
        modifier = modifier
    )
}

@Composable
fun TaskItem(
    task: Task,
    onTaskClick: (Task) -> Unit,
    onTaskDelete: (Task) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(8.dp))

        // Task Name
        Text(
            text = task.name,
            color = Color.White, // Set text color to white
            fontSize = 20.sp,    // Set font size to 20sp
            fontWeight = androidx.compose.ui.text.font.FontWeight.Normal
        )

        Spacer(modifier = Modifier.weight(1f))

        // Custom checkbox: Circle with a checkmark when checked
        Box(
            modifier = Modifier
                .size(24.dp)
                .border(1.dp, Color.White, CircleShape) // Add a white border around the circle
                .background(
                    color = if (task.isCompleted) Color.Gray else Color.Transparent,
                    shape = CircleShape
                )
                .clickable {
                    onTaskClick(task.copy(isCompleted = !task.isCompleted))
                },
            contentAlignment = Alignment.Center
        ) {
            if (task.isCompleted) {
                Icon(
                    painter = painterResource(id = R.drawable.checkicon), // A checkmark icon
                    contentDescription = "Checked",
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
            }
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

@Composable
fun DividerLine() {
    Box(
        modifier = Modifier
            .width(350.dp)
            .height(1.dp)
            .background(Color.Gray)
    )
}

@Composable
fun TaskDivider() {
    Box(
        modifier = Modifier
            .width(350.dp)
            .height(1.dp)
            .background(Color.Gray)
    )
}

@Composable
fun RoundedIconButton(
    onClick: () -> Unit,
    icon: Int,
    contentDescription: String
) {
    Button(onClick = onClick) {
        // Add icon here
    }
}


@Preview
@Composable
fun TaskTitlePreview() {
    TaskTitle(name = "Tasks")
}

@Preview
@Composable
fun TaskItemPreview() {
    TaskItem(
        task = Task(name = "Task 1", isCompleted = false),
        onTaskClick = {},
        onTaskDelete = {}
    )
}

@Preview
@Composable
fun TaskListPreview() {
    TaskList(
        tasks = listOf(
            Task(name = "Task 1", isCompleted = false),
            Task(name = "Task 2", isCompleted = true)
        ),
        onTaskClick = {},
        onTaskDelete = {}
    )
}