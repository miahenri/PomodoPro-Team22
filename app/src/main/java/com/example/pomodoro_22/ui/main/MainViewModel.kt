package com.example.pomodoro_22.ui.main

import android.app.Activity
import android.app.ActivityManager
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import android.os.CountDownTimer
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.pomodoro_22.ui.settings.SettingsViewModel

enum class PomodoroPhase {
    WORK, SHORT_BREAK, LONG_BREAK
}

class MainViewModel(application: Application, private val settingsViewModel: SettingsViewModel) : AndroidViewModel(application) {

    private val sharedPreferences: SharedPreferences = application.getSharedPreferences("pomodoro_prefs", Context.MODE_PRIVATE)
    private val context: Context = application.applicationContext

    // Countdown timer for Pomodoro
    private var timer: CountDownTimer? = null

    // Holds the current phase of Pomodoro (WORK, SHORT_BREAK, LONG_BREAK)
    private val _phase = MutableStateFlow(PomodoroPhase.WORK)
    val phase = _phase.asStateFlow()

    // Tracks the remaining time in milliseconds
    private val _timeLeftInMillis = MutableStateFlow(settingsViewModel.workTimeMinutes.value * 60 * 1000L)
    val timeLeftInMillis = _timeLeftInMillis.asStateFlow()

    // Indicates whether the timer is running
    private val _timerRunning = MutableStateFlow(false)
    val timerRunning = _timerRunning.asStateFlow()

    // Counter for completed work sessions
    private var completedWorkSessions = 0

    init {
        createNotificationChannel() // Initialize the notification channel for timer notifications
        restoreTimerState() // Restore previous timer state if available
    }

    // Returns the total duration for the current phase
    val totalTimeForCurrentPhase: Long
        get() = when (_phase.value) {
            PomodoroPhase.WORK -> settingsViewModel.workTimeMinutes.value * 60 * 1000L
            PomodoroPhase.SHORT_BREAK -> settingsViewModel.shortBreakTimeMinutes.value * 60 * 1000L
            PomodoroPhase.LONG_BREAK -> settingsViewModel.longBreakTimeMinutes.value * 60 * 1000L
        }

    // Checks if the foreground service (Pomodoro timer) is currently running
    private fun isServiceRunning(serviceClass: Class<*>): Boolean {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (service in activityManager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                return true
            }
        }
        return false
    }

    // Requests notification permission (for Android TIRAMISU and above)
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun checkAndRequestNotificationPermission(activity: Activity) {
        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 1)
        }
    }

    // Restores the saved state of the Pomodoro timer
    fun restoreTimerState() {
        _timeLeftInMillis.value = sharedPreferences.getLong("timeLeftInMillis", settingsViewModel.workTimeMinutes.value * 60 * 1000L)
        val phaseName = sharedPreferences.getString("currentPhase", PomodoroPhase.WORK.name)
        _phase.value = PomodoroPhase.valueOf(phaseName ?: PomodoroPhase.WORK.name)
        _timerRunning.value = sharedPreferences.getBoolean("timerRunning", false)
        completedWorkSessions = sharedPreferences.getInt("completedWorkSessions", 0)

        // If the service is still running, resume the timer
        if (_timerRunning.value && isServiceRunning(PomodoroForegroundService::class.java)) {
            startForegroundService(context, _timeLeftInMillis.value, _phase.value)
            startTimer()  // Make sure the timer starts with the correct phase
        }
    }

    // Starts the Pomodoro timer and foreground service
    fun startTimer() {
        timer?.cancel() // Cancel any running timer

        // Start the foreground service to sync the timer in the notification
        startForegroundService(context, _timeLeftInMillis.value, _phase.value)

        // Create and start a new countdown timer
        timer = object : CountDownTimer(_timeLeftInMillis.value, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _timeLeftInMillis.value = millisUntilFinished
            }

            override fun onFinish() {
                _timeLeftInMillis.value = 0L
                _timerRunning.value = false
                //stopForegroundService(context) // Stop the service when the timer ends
                showNotification() // Show a notification when the timer finishes
                skipPhase()
            }
        }.start()

        _timerRunning.value = true // Set the timer as running
    }

    // Handles the transition when a Pomodoro phase finishes
    private fun onTimerFinish() {
        val safeWorkSessionCount = if (settingsViewModel.workSessionCount.value <= 0) 4 else settingsViewModel.workSessionCount.value

        // Switch to the next phase depending on the completed work sessions
        when (_phase.value) {
            PomodoroPhase.WORK -> {
                completedWorkSessions++
                _phase.value = if (completedWorkSessions % safeWorkSessionCount == 0) {
                    PomodoroPhase.LONG_BREAK
                } else {
                    PomodoroPhase.SHORT_BREAK
                }
            }
            PomodoroPhase.SHORT_BREAK -> _phase.value = PomodoroPhase.WORK
            PomodoroPhase.LONG_BREAK -> {
                _phase.value = PomodoroPhase.WORK
                completedWorkSessions = 0
            }
        }

        _timeLeftInMillis.value = totalTimeForCurrentPhase // Reset time for the next phase
        startForegroundService(context, _timeLeftInMillis.value, _phase.value) // Sync the phase with the notification
        startTimer() // Start the next phase's timer
    }

    // Stops the running timer
    fun stopTimer() {
        timer?.cancel() // Cancel the running timer
        _timerRunning.value = false
        stopForegroundService(context) // Stop the foreground service
    }

    // Resets the timer to the initial work phase and stops the service
    fun resetTimer() {
        timer?.cancel() // Cancel the running timer
        _phase.value = PomodoroPhase.WORK
        _timeLeftInMillis.value = settingsViewModel.workTimeMinutes.value * 60 * 1000L
        _timerRunning.value = false
        completedWorkSessions = 0 // Reset work session count

        // Save the reset state
        sharedPreferences.edit().apply {
            putString("currentPhase", PomodoroPhase.WORK.name)
            putLong("timeLeftInMillis", _timeLeftInMillis.value)
            putInt("completedWorkSessions", completedWorkSessions)
            apply()
        }

        stopForegroundService(context) // Stop the foreground service to reset the notification
    }

    // Skips to the next phase of Pomodoro and syncs with the notification
    fun skipPhase() {
        timer?.cancel() // Cancel the running timer
        transitionToNextPhase() // Move to the next phase
        _timeLeftInMillis.value = totalTimeForCurrentPhase
        startForegroundService(context, _timeLeftInMillis.value, _phase.value) // Sync the new phase with the notification
        startTimer() // Start the next phase's timer
    }

    // Logic to switch between Pomodoro phases
    private fun transitionToNextPhase() {
        val safeWorkSessionCount = if (settingsViewModel.workSessionCount.value <= 0) 4 else settingsViewModel.workSessionCount.value

        when (_phase.value) {
            PomodoroPhase.WORK -> {
                completedWorkSessions++
                _phase.value = if (completedWorkSessions % safeWorkSessionCount == 0) {
                    PomodoroPhase.LONG_BREAK
                } else {
                    PomodoroPhase.SHORT_BREAK
                }
            }
            PomodoroPhase.SHORT_BREAK, PomodoroPhase.LONG_BREAK -> {
                _phase.value = PomodoroPhase.WORK
            }
        }

        // Save the current phase after every transition
        sharedPreferences.edit().putString("currentPhase", _phase.value.name).apply()
    }

    // Displays a notification when the Pomodoro timer finishes
    private fun showNotification() {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        val builder = NotificationCompat.Builder(context, "TIMER_CHANNEL")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("Pomodoro Timer")
            .setContentText("Ein Timer ist abgelaufen!") // Timer has finished
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
        with(NotificationManagerCompat.from(context)) {
            if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
                notify(1, builder.build())
            }
        }
    }

    // Creates a notification channel (needed for Android O and above)
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Pomodoro Timer"
            val descriptionText = "Notifications for Pomodoro Timer"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("TIMER_CHANNEL", name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    // Starts the foreground service with the timer information and current phase
    private fun startForegroundService(context: Context, timeLeftInMillis: Long, currentPhase: PomodoroPhase) {
        val intent = Intent(context, PomodoroForegroundService::class.java).apply {
            action = PomodoroForegroundService.ACTION_START
            putExtra("time_left", timeLeftInMillis)
            putExtra("current_phase", currentPhase.name) // Pass the current phase to the service
        }
        ContextCompat.startForegroundService(context, intent)
    }

    // Stops the foreground service
    private fun stopForegroundService(context: Context) {
        val intent = Intent(context, PomodoroForegroundService::class.java).apply {
            action = PomodoroForegroundService.ACTION_STOP
        }
        context.startService(intent)
    }
}