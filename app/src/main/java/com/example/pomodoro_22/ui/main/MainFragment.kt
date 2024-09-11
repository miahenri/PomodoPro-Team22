package com.example.pomodoro_22.ui.main

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.core.content.ContextCompat
import com.example.pomodoro_22.ui.task.TaskList
import com.example.pomodoro_22.ui.task.TaskViewModel

@Composable
fun MainScreen(
    navController: NavHostController,
    taskViewModel: TaskViewModel,
    timerViewModel: MainViewModel
) {
    val context = navController.context  // Get context for SharedPreferences and Service interactions
    val timerRunning by timerViewModel.timerRunning.collectAsState()
    val timeLeftInMillis by timerViewModel.timeLeftInMillis.collectAsState()
    val currentPhase by timerViewModel.phase.collectAsState()
    val totalTimeForCurrentPhase = timerViewModel.totalTimeForCurrentPhase

    // Restore saved time from SharedPreferences when the Composable is first launched
    LaunchedEffect(Unit) {
        val sharedPreferences = context.getSharedPreferences("pomodoro_prefs", Context.MODE_PRIVATE)
        val savedTime = sharedPreferences.getLong("time_left", 0)

        if (savedTime > 0 && !timerRunning) {
            timerViewModel.setTimeLeft(savedTime)
            val serviceIntent = Intent(context, PomodoroForegroundService::class.java)
            serviceIntent.action = PomodoroForegroundService.ACTION_START
            serviceIntent.putExtra("time_left", savedTime)
            ContextCompat.startForegroundService(context, serviceIntent)
        }
    }

    // When timer is stopped, save the current time left to SharedPreferences
    DisposableEffect(Unit) {
        onDispose {
            val sharedPreferences = context.getSharedPreferences("pomodoro_prefs", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putLong("time_left", timeLeftInMillis)
            editor.apply()
        }
    }

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
                onClick = { navController.navigate("task_screen") },
                icon = com.example.pomodoro_22.R.drawable.addtaskicon,
                contentDescription = "Go to Tasks"
            )

            PomodoroTitle(name = "Pomodoro")

            RoundedIconButton(
                onClick = { navController.navigate("settings_screen") },
                icon = com.example.pomodoro_22.R.drawable.settingsicon,
                contentDescription = "Settings"
            )
        }

        DividerLine()

        PomodoroTimer(
            timeInMillis = timeLeftInMillis,
            timerRunning = timerRunning,
            currentPhase = currentPhase,
            totalTimeForCurrentPhase = totalTimeForCurrentPhase,  // Pass the dynamic total time here
            onStartTimer = {
                timerViewModel.startTimer()
                // Start foreground service
                val serviceIntent = Intent(context, PomodoroForegroundService::class.java)
                serviceIntent.action = PomodoroForegroundService.ACTION_START
                serviceIntent.putExtra("time_left", timeLeftInMillis)
                ContextCompat.startForegroundService(context, serviceIntent)
            },
            onStopTimer = {
                timerViewModel.stopTimer()
                // Stop foreground service
                val serviceIntent = Intent(context, PomodoroForegroundService::class.java)
                serviceIntent.action = PomodoroForegroundService.ACTION_STOP
                ContextCompat.startForegroundService(context, serviceIntent)
            },
            onResetTimer = { timerViewModel.resetTimer() },
            onSkip = { timerViewModel.skipPhase() }  // Add skip action here
        )

        DividerLine()

        TaskList(
            tasks = taskViewModel.tasks.observeAsState(emptyList()).value,
            onTaskClick = { updatedTask -> taskViewModel.updateTask(updatedTask) },
            onTaskDelete = { task -> taskViewModel.deleteTask(task) }
        )
    }
}