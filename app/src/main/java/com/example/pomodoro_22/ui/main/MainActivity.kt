package com.example.pomodoro_22.ui.main

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pomodoro_22.ui.main.ui.theme.*
import com.example.pomodoro_22.ui.settings.SettingsFragment
import com.example.pomodoro_22.ui.settings.SettingsViewModel
import com.example.pomodoro_22.ui.settings.SettingsViewModelFactory
import com.example.pomodoro_22.ui.task.TaskFragment
import com.example.pomodoro_22.ui.task.TaskViewModel
import com.example.pomodoro_22.ui.task.TaskViewModelFactory
import com.example.pomodoro_22.repository.TaskRepository

class MainActivity : ComponentActivity() {
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the repository
        Log.d("MainActivity", "Initializing TaskRepository")
        val taskRepository = TaskRepository(applicationContext)

        // Use TaskViewModelFactory to create the ViewModel
        Log.d("MainActivity", "Creating TaskViewModelFactory")
        val taskViewModelFactory = TaskViewModelFactory(taskRepository)
        val taskViewModel: TaskViewModel = ViewModelProvider(this, taskViewModelFactory).get(TaskViewModel::class.java)

        val settingsViewModelFactory = SettingsViewModelFactory(application)
        val settingsViewModel: SettingsViewModel = ViewModelProvider(this, settingsViewModelFactory).get(SettingsViewModel::class.java)

        val mainViewModelFactory = MainViewModelFactory(application, settingsViewModel)
        mainViewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)
        Log.d("MainActivity", "TaskViewModel created")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            mainViewModel.checkAndRequestNotificationPermission(this)
        }

        setContent {
            Pomodoro22Theme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = darkMode
                ) {
                    NavHost(navController = navController, startDestination = "main_screen") {
                        composable("main_screen") {
                            Log.d("Navigation", "Navigated to MainScreen")
                            MainScreen(navController, taskViewModel, mainViewModel)
                        }

                        composable("settings_screen") {
                            Log.d("Navigation", "Navigated to SettingsFragment")
                            SettingsFragment(navController, settingsViewModel, mainViewModel)
                        }

                        composable("task_screen") {
                            Log.d("Navigation", "Navigated to TaskFragment")
                            TaskFragment(
                                navController = navController,
                                taskViewModel = taskViewModel
                            )
                        }
                    }
                }
            }
        }
    }
}