package com.example.pomodoro_22.ui.main

import android.app.*
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_SYNC
import android.content.pm.PackageManager
import android.os.*
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.example.pomodoro_22.R

class PomodoroForegroundService : Service() {

    private var timer: CountDownTimer? = null
    private var timeLeftInMillis: Long = 0
    private var currentPhase: PomodoroPhase = PomodoroPhase.WORK // Default phase

    // Handle the start and stop commands for the foreground service
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val action = intent?.action ?: return START_NOT_STICKY
        when (action) {
            ACTION_START -> {
                timeLeftInMillis = intent?.getLongExtra("time_left", 0) ?: 0
                currentPhase = PomodoroPhase.valueOf(intent?.getStringExtra("current_phase") ?: PomodoroPhase.WORK.name)
                createNotificationChannel()
                startForegroundService(timeLeftInMillis, currentPhase)
                updateNotification(timeLeftInMillis, currentPhase)
            }
            ACTION_STOP -> stopForegroundService()
            ACTION_RESET -> stopForegroundService()
            ACTION_SYNC -> {
                timeLeftInMillis = intent.getLongExtra("time_left", 0)
                currentPhase = PomodoroPhase.valueOf(intent.getStringExtra("current_phase")!!)
                updateNotification(timeLeftInMillis, currentPhase)
                startForegroundService(timeLeftInMillis, currentPhase)
            }
            ACTION_SKIP -> {
                val timeLeftInMillis = intent?.getLongExtra("time_left", 0) ?: 0
                val currentPhase = PomodoroPhase.valueOf(intent?.getStringExtra("current_phase") ?: PomodoroPhase.WORK.name)
                startForegroundService(timeLeftInMillis, currentPhase)
            }
        }
        return START_NOT_STICKY
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

    // Start the foreground service with the countdown timer
    private fun startForegroundService(timeLeftInMillis: Long, currentPhase: PomodoroPhase) {
        val notification = createNotification(timeLeftInMillis, currentPhase)
        startForeground(NOTIFICATION_ID, notification)
    }

    // Stop the foreground service and cancel the timer
    private fun stopForegroundService() {
        timer?.cancel() // Cancel the running timer in the foreground service
        stopForeground(STOP_FOREGROUND_DETACH) // Remove the notification from the foreground
        stopForeground(STOP_FOREGROUND_REMOVE) // Remove the notification from the foreground
        stopSelf() // Stop the service completely
        //NotificationManagerCompat.from(this).cancel(NOTIFICATION_ID) // Remove the notification
    }

    // Update the notification with the remaining time on every tick of the timer
    private fun updateNotification(millisUntilFinished: Long, currentPhase: PomodoroPhase = this.currentPhase) {
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val notification = createNotification(millisUntilFinished, currentPhase)
        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    // Create a notification to show the remaining Pomodoro time
    private fun createNotification(millisUntilFinished: Long, currentPhase: PomodoroPhase): Notification {
        val minutes = (millisUntilFinished / 1000) / 60
        val seconds = (millisUntilFinished / 1000) % 60

        // Intent to launch the app when the notification is clicked
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE)

        return NotificationCompat.Builder(this, TIMER_CHANNEL_ID)
            .setContentTitle("Pomodoro Timer - $currentPhase") // Show the current phase
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
        const val ACTION_START = "START"
        const val ACTION_STOP = "STOP"
        const val ACTION_RESET = "RESET"
        const val ACTION_SKIP = "SKIP"
        const val ACTION_SYNC = "SYNC"
        const val NOTIFICATION_ID = 1
        const val TIMER_CHANNEL_ID = "timer_channel"

    }
}