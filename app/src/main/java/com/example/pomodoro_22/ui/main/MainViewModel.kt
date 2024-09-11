package com.example.pomodoro_22.ui.main

import android.app.Activity
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
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewModelScope
import com.example.pomodoro_22.ui.settings.SettingsViewModel

enum class PomodoroPhase {
    WORK, SHORT_BREAK, LONG_BREAK
}

class MainViewModel(application: Application, private val settingsViewModel: SettingsViewModel) : AndroidViewModel(application) {

    private val sharedPreferences: SharedPreferences = application.getSharedPreferences("pomodoro_prefs", Context.MODE_PRIVATE)
    private val context: Context = application.applicationContext

    private var timer: CountDownTimer? = null
    private val _phase = MutableStateFlow(PomodoroPhase.WORK)
    val phase = _phase.asStateFlow()

    private val _timeLeftInMillis = MutableStateFlow(settingsViewModel.workTimeMinutes.value * 60 * 1000L)
    val timeLeftInMillis = _timeLeftInMillis.asStateFlow()

    private val _timerRunning = MutableStateFlow(false)
    val timerRunning = _timerRunning.asStateFlow()

    private var completedWorkSessions = 0

    init {
        createNotificationChannel()
    }

    // Returns the total time for the current phase
    val totalTimeForCurrentPhase: Long
        get() = when (_phase.value) {
            PomodoroPhase.WORK -> settingsViewModel.workTimeMinutes.value * 60 * 1000L
            PomodoroPhase.SHORT_BREAK -> settingsViewModel.shortBreakTimeMinutes.value * 60 * 1000L
            PomodoroPhase.LONG_BREAK -> settingsViewModel.longBreakTimeMinutes.value * 60 * 1000L
        }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun checkAndRequestNotificationPermission(activity: Activity) {
        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 1)
        }
    }

    fun setTimeLeft(timeLeft: Long) {
        _timeLeftInMillis.value = timeLeft
        startTimer()
    }

    fun startTimer() {
        // Cancel the existing timer if it's running
        timer?.cancel()

        // Start the foreground service
        startForegroundService(context, _timeLeftInMillis.value)

        // If the timer was previously stopped, continue from where it left off
        val remainingTime = _timeLeftInMillis.value

        // Start a new timer with the remaining time
        timer = object : CountDownTimer(remainingTime, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _timeLeftInMillis.value = millisUntilFinished
            }

            override fun onFinish() {
                _timeLeftInMillis.value = 0L
                _timerRunning.value = false

                // Stop the foreground service when the timer finishes
                stopForegroundService(context)
                onTimerFinish()
                showNotification()
            }
        }.start()

        // Set the timer to running
        _timerRunning.value = true
    }

    private fun onTimerFinish() {
        // Ensure workSessionCount is not zero to avoid divide by zero
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
            PomodoroPhase.SHORT_BREAK -> _phase.value = PomodoroPhase.WORK
            PomodoroPhase.LONG_BREAK -> {
                _phase.value = PomodoroPhase.WORK
                completedWorkSessions = 0
            }
        }

        // Reset the time for the new phase
        _timeLeftInMillis.value = totalTimeForCurrentPhase
        startTimer()  // Start the timer for the next phase
    }

    fun stopTimer() {
        timer?.cancel()
        _timerRunning.value = false

        // Stop the foreground service when the timer is manually stopped
        stopForegroundService(context)
    }

    fun resetTimer() {
        timer?.cancel()
        _phase.value = PomodoroPhase.WORK
        _timeLeftInMillis.value = settingsViewModel.workTimeMinutes.value * 60 * 1000L
        _timerRunning.value = false
        completedWorkSessions = 0
    }

    fun skipPhase() {
        // Cancel the current timer
        timer?.cancel()

        // Transition to the next phase
        transitionToNextPhase()

        // Reset the time for the new phase and start the timer
        _timeLeftInMillis.value = totalTimeForCurrentPhase
        startTimer()  // Start the timer for the next phase
    }

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
            PomodoroPhase.SHORT_BREAK, PomodoroPhase.LONG_BREAK -> _phase.value = PomodoroPhase.WORK
        }
    }


    private fun showNotification() {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        val builder = NotificationCompat.Builder(context, "TIMER_CHANNEL")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("Pomodoro Timer")
            .setContentText("Ein Timer ist abgelaufen!")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
        with(NotificationManagerCompat.from(context)) {
            if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
                notify(1, builder.build())
            }
        }
    }

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

    fun startForegroundService(context: Context, timeLeftInMillis: Long) {
        val intent = Intent(context, PomodoroForegroundService::class.java).apply {
            action = PomodoroForegroundService.ACTION_START
            putExtra("time_left", timeLeftInMillis)
        }
        ContextCompat.startForegroundService(context, intent)
    }

    fun stopForegroundService(context: Context) {
        val intent = Intent(context, PomodoroForegroundService::class.java).apply {
            action = PomodoroForegroundService.ACTION_STOP
        }
        context.startService(intent)
    }

}