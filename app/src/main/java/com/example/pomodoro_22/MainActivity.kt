package com.example.pomodoro_22

import android.app.ActivityManager
import android.content.Context
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
import com.example.pomodoro_22.viewmodel.SettingsViewModel
import com.example.pomodoro_22.viewmodel.SettingsViewModelFactory
import com.example.pomodoro_22.ui.task.TaskFragment
import com.example.pomodoro_22.viewmodel.TaskViewModel
import com.example.pomodoro_22.viewmodel.TaskViewModelFactory
import com.example.pomodoro_22.repository.TaskRepository
import com.example.pomodoro_22.ui.main.MainScreen
import com.example.pomodoro_22.viewmodel.MainViewModel
import com.example.pomodoro_22.viewmodel.MainViewModelFactory
import com.example.pomodoro_22.service.PomodoroForegroundService

class MainActivity : ComponentActivity() {

    // Checks if the foreground service is running by looking for it in the running services.
    private fun isServiceRunning(serviceClass: Class<*>): Boolean {
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (service in activityManager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                return true
            }
        }
        return false
    }

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize TaskRepository and TaskViewModel for managing tasks
        Log.d("MainActivity", "Initializing TaskRepository")
        val taskRepository = TaskRepository(applicationContext)

        // TaskViewModelFactory is used to create the TaskViewModel instance
        Log.d("MainActivity", "Creating TaskViewModelFactory")
        val taskViewModelFactory = TaskViewModelFactory(taskRepository)
        val taskViewModel: TaskViewModel = ViewModelProvider(this, taskViewModelFactory).get(
            TaskViewModel::class.java)

        // SettingsViewModel manages user preferences (Pomodoro settings)
        val settingsViewModelFactory = SettingsViewModelFactory(application)
        val settingsViewModel: SettingsViewModel = ViewModelProvider(this, settingsViewModelFactory).get(
            SettingsViewModel::class.java)

        // MainViewModel manages the core Pomodoro timer logic and interacts with Task and Settings ViewModels
        val mainViewModelFactory = MainViewModelFactory(application, settingsViewModel)
        mainViewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)
        Log.d("MainActivity", "TaskViewModel created")

        // Request notification permissions if the Android version is TIRAMISU or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            mainViewModel.checkAndRequestNotificationPermission(this)
        }

        // Check if the foreground service is already running (in case the app was restarted or resumed)
        if (isServiceRunning(PomodoroForegroundService::class.java)) {
            // If the service is running, restore the Pomodoro timer state in the ViewModel
            mainViewModel.restoreTimerState()
        }

        // Set up the UI using Jetpack Compose and navigation with NavHost
        setContent {
            Pomodoro22Theme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = darkMode
                ) {
                    // Define navigation between different screens (Main, Settings, Tasks)
                    NavHost(navController = navController, startDestination = "main_screen") {

                        // Main Pomodoro timer screen
                        composable("main_screen") {
                            Log.d("Navigation", "Navigated to MainScreen")
                            MainScreen(navController, taskViewModel, mainViewModel)
                        }

                        // Settings screen for Pomodoro settings
                        composable("settings_screen") {
                            Log.d("Navigation", "Navigated to SettingsFragment")
                            SettingsFragment(navController, settingsViewModel, mainViewModel)
                        }

                        // Task screen for managing tasks
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