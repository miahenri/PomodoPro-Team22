package com.example.pomodoro_22.ui.main

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.pomodoro_22.ui.task.TaskList
import com.example.pomodoro_22.ui.task.TaskViewModel

@Composable
fun MainScreen(
    navController: NavHostController,
    taskViewModel: TaskViewModel,
    timerViewModel: MainViewModel
) {
    Log.d("MainScreen", "MainScreen Composable created")

    val timerRunning by timerViewModel.timerRunning.collectAsState()
    val timeLeftInMillis by timerViewModel.timeLeftInMillis.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
            .padding(top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            RoundedIconButton(
                onClick = {
                    navController.navigate("task_screen")
                },
                icon = com.example.pomodoro_22.R.drawable.addtaskicon,
                contentDescription = "Go to Tasks"
            )

            PomodoroTitle(name = "Pomodoro")

            RoundedIconButton(
                onClick = {
                    navController.navigate("settings_screen")
                },
                icon = com.example.pomodoro_22.R.drawable.settingsicon,
                contentDescription = "Settings"
            )
        }

        DividerLine()

        // Timer operations
        PomodoroTimer(
            timeInMillis = timeLeftInMillis,
            timerRunning = timerRunning,
            onStartTimer = { timerViewModel.startTimer() },
            onStopTimer = { timerViewModel.stopTimer() },
            onResetTimer = { timerViewModel.resetTimer() }
        )

        DividerLine()

        TaskList(
            tasks = taskViewModel.tasks.observeAsState(emptyList()).value,
            onTaskClick = { updatedTask ->
                taskViewModel.updateTask(updatedTask)
            },
            onTaskDelete = { task ->
                taskViewModel.deleteTask(task)
            }
        )
    }
}
