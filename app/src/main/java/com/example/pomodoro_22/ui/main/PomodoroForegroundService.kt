package com.example.pomodoro_22.ui.main

import android.app.*
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.*
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.example.pomodoro_22.R
import com.example.pomodoro_22.ui.main.*

class PomodoroForegroundService : Service() {

    private var timer: CountDownTimer? = null
    private var timeLeftInMillis: Long = 0
    private var currentPhase: PomodoroPhase = PomodoroPhase.WORK // Default phase

    // Handle the start and stop commands for the foreground service
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        if (timer == null || timeLeftInMillis == 0L) {
            startForegroundService()  // Ensure only one timer instance is running
        }

        val action = intent?.action
        timeLeftInMillis = intent?.getLongExtra("time_left", 0) ?: 0
        currentPhase = PomodoroPhase.valueOf(intent?.getStringExtra("current_phase") ?: PomodoroPhase.WORK.name)

        when (action) {
            ACTION_START -> {
                createNotificationChannel()
                startForegroundService()
            }
            ACTION_STOP -> stopForegroundService()
        }

        return START_NOT_STICKY // Ensures the service does not restart automatically if killed by the system
    }

    // Check if notification permissions are granted (Android 13+ specific)
    private fun checkNotificationPermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED
        } else {
            true
        }
    }

    // Create a notification channel (required for Android O and higher)
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                TIMER_CHANNEL_ID,
                "Pomodoro Timer Channel",
                NotificationManager.IMPORTANCE_LOW
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
        }
    }

    // Track the number of completed work sessions
    private var completedWorkSessions: Int = 0

    // Save the timer state to shared preferences (to persist across service or app restarts)
    private fun saveTimerStateToSharedPreferences() {
        val sharedPreferences = getSharedPreferences("pomodoro_prefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().apply {
            putLong("timeLeftInMillis", timeLeftInMillis)
            putString("currentPhase", currentPhase.name)  // Save the current Pomodoro phase
            putBoolean("timerRunning", true)  // Indicate that the timer is running
            putInt("completedWorkSessions", completedWorkSessions)  // Save the number of completed work sessions
            apply()
        }
    }

    // Start the foreground service with the countdown timer
    private fun startForegroundService() {
        val notification = createNotification(timeLeftInMillis, currentPhase) // Create the initial notification
        startForeground(NOTIFICATION_ID, notification) // Start the service in the foreground with the notification

        // Cancel any existing timer and start a new countdown timer
        timer?.cancel()
        timer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                saveTimerStateToSharedPreferences()
                updateNotification(millisUntilFinished) // Update the notification to reflect the remaining time
            }

            override fun onFinish() {
                showNotification()
                stopForegroundService()
            }
        }.start()
    }

    private fun showNotification() {
        // Only show the notification if permission is granted
        if (checkNotificationPermission()) {
            val notification = createNotification(0, currentPhase) // Timer finished state
            try {
                NotificationManagerCompat.from(this).notify(NOTIFICATION_ID, notification)
            } catch (e: SecurityException) {
                // Handle the security exception in case permission is revoked during runtime
                e.printStackTrace()
            }
        }
    }

    // Stop the foreground service and cancel the timer
    private fun stopForegroundService() {
        timer?.cancel() // Cancel the running timer in the foreground service
        stopForeground(true) // Remove the service from the foreground
        stopSelf() // Stop the service completely
        //NotificationManagerCompat.from(this).cancel(NOTIFICATION_ID) // Remove the notification
    }

    // Update the notification with the remaining time on every tick of the timer
    private fun updateNotification(millisUntilFinished: Long) {
        val notification = createNotification(millisUntilFinished, currentPhase)
        if (checkNotificationPermission()) {
            NotificationManagerCompat.from(this).notify(NOTIFICATION_ID, notification)
        }
    }

    // Create a notification to show the remaining Pomodoro time
    private fun createNotification(millisUntilFinished: Long, currentPhase: PomodoroPhase): Notification {
        val minutes = (millisUntilFinished / 1000) / 60
        val seconds = (millisUntilFinished / 1000) % 60

        // Intent to launch the app when the notification is clicked
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE)

        return NotificationCompat.Builder(this, TIMER_CHANNEL_ID)
            .setContentTitle("Pomodoro Timer - ${getPhaseDisplayName(currentPhase)}") // Show the user-friendly phase
            .setContentText(String.format("%02d:%02d remaining", minutes, seconds)) // Display remaining time
            .setSmallIcon(R.drawable.timericon)  // Replace with the appropriate icon
            .setContentIntent(pendingIntent) // Open the app when the notification is tapped
            .setOngoing(true) // Prevent the user from swiping away the notification
            .setOnlyAlertOnce(true) // Only alert the user once when the notification is updated
            .build()
    }

    // Binding is not required for this service
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    companion object {
        const val ACTION_START = "START" // Action to start the foreground service
        const val ACTION_STOP = "STOP" // Action to stop the foreground service
        const val NOTIFICATION_ID = 1 // ID for the notification
        const val TIMER_CHANNEL_ID = "timer_channel" // ID for the notification channel
    }

    private fun getPhaseDisplayName(phase: PomodoroPhase): String {
        return when (phase) {
            PomodoroPhase.WORK -> "Work Session"
            PomodoroPhase.SHORT_BREAK -> "Short Break"
            PomodoroPhase.LONG_BREAK -> "Long Break"
        }
    }
}