package com.example.pomodoro_22.ui.main

import android.app.Activity
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
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

enum class PomodoroPhase {
    WORK, SHORT_BREAK, LONG_BREAK
}

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val context: Context = application.applicationContext

    init {
        createNotificationChannel()
    }

    private var timer: CountDownTimer? = null

    // Initial phase is always WORK
    private val _phase = MutableStateFlow(PomodoroPhase.WORK) // Track current phase
    val phase = _phase.asStateFlow()

    // Initialize the time based on the WORK phase
    private val _timeLeftInMillis = MutableStateFlow(totalTimeForCurrentPhase)
    val timeLeftInMillis = _timeLeftInMillis.asStateFlow()

    private val _timerRunning = MutableStateFlow(false)
    val timerRunning = _timerRunning.asStateFlow()

    private var workSessionCount = 0 // Count how many work sessions have been completed

    // This property returns the total time of the active phase (work, short break, long break)
    val totalTimeForCurrentPhase: Long
        get() = when (_phase.value) {
            PomodoroPhase.WORK -> 25 * 60 * 1000L
            PomodoroPhase.SHORT_BREAK -> 5 * 60 * 1000L
            PomodoroPhase.LONG_BREAK -> 50 * 60 * 1000L
        }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun checkAndRequestNotificationPermission(activity: Activity) {
        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 1)
        }
    }

    fun startTimer() {
        timer?.cancel()

        _timeLeftInMillis.value = totalTimeForCurrentPhase

        timer = object : CountDownTimer(_timeLeftInMillis.value, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _timeLeftInMillis.value = millisUntilFinished
            }

            override fun onFinish() {
                _timeLeftInMillis.value = 0L
                _timerRunning.value = false
                onTimerFinish() // Handle transition to next phase
                showNotification()
            }
        }.start()
        _timerRunning.value = true
    }

    private fun onTimerFinish() {
        when (_phase.value) {
            PomodoroPhase.WORK -> {
                workSessionCount++
                _phase.value = if (workSessionCount % 4 == 0) {
                    PomodoroPhase.LONG_BREAK
                } else {
                    PomodoroPhase.SHORT_BREAK
                }
            }
            PomodoroPhase.SHORT_BREAK -> {
                _phase.value = PomodoroPhase.WORK
            }
            PomodoroPhase.LONG_BREAK -> {
                _phase.value = PomodoroPhase.WORK
                workSessionCount = 0 // Reset work session count after long break
            }
        }
        startTimer() // Automatically start the next phase
    }

    fun stopTimer() {
        timer?.cancel()
        _timerRunning.value = false
    }

    fun resetTimer() {
        timer?.cancel()
        _phase.value = PomodoroPhase.WORK
        _timeLeftInMillis.value = 25 * 60 * 1000L
        _timerRunning.value = false
        workSessionCount = 0
    }

    fun skipPhase() {
        onTimerFinish()
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

}