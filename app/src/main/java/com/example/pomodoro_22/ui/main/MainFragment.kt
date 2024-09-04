package com.example.pomodoro_22.ui.main

import android.os.CountDownTimer
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.pomodoro_22.R
import com.example.pomodoro_22.ui.task.TaskList
import com.example.pomodoro_22.ui.task.TaskViewModel

@Composable
fun MainScreen(navController: NavHostController, taskViewModel: TaskViewModel) {
    Log.d("MainScreen", "MainScreen Composable created")

    var timerRunning by remember { mutableStateOf(false) }
    var timer: CountDownTimer? by remember { mutableStateOf(null) }
    var timeLeftInMillis by remember { mutableStateOf(25 * 60 * 1000L) }

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
                    Log.d("MainScreen", "Navigating to Task screen")
                    navController.navigate("task_screen")
                },
                icon = R.drawable.addtaskicon,
                contentDescription = "Go to Tasks"
            )

            PomodoroTitle(name = "Pomodoro")

            RoundedIconButton(
                onClick = {
                    Log.d("MainScreen", "Navigating to Settings screen")
                    navController.navigate("settings_screen")
                },
                icon = R.drawable.settingsicon,
                contentDescription = "Settings"
            )
        }

        DividerLine()

        // Timer operations
        PomodoroTimer(
            timeInMillis = timeLeftInMillis,
            timerRunning = timerRunning,
            onStartTimer = {
                Log.d("PomodoroTimer", "Start button clicked, starting timer")
                timer?.cancel()
                timer = startTimer(timeLeftInMillis) { millisUntilFinished ->
                    timeLeftInMillis = millisUntilFinished
                    Log.d("PomodoroTimer", "Timer tick: $timeLeftInMillis milliseconds left")
                }
                timerRunning = true
            },
            onStopTimer = {
                Log.d("PomodoroTimer", "Stop button clicked, stopping timer")
                timer?.cancel()
                timerRunning = false
            }
        ) {
            Log.d("PomodoroTimer", "Reset button clicked, resetting timer")
            timer?.cancel()
            timeLeftInMillis = 25 * 60 * 1000L
            timerRunning = false
        }

        DividerLine()

        // Log tasks being displayed and any updates
        TaskList(
            tasks = taskViewModel.tasks.observeAsState(emptyList()).value,
            onTaskClick = { updatedTask ->
                Log.d("MainScreen", "Task clicked: ${updatedTask.id}")
                taskViewModel.updateTask(updatedTask)
            },
            onTaskDelete = { task ->
                Log.d("MainScreen", "Task deleted: ${task.id}")
                taskViewModel.deleteTask(task)
            }
        )
    }
}