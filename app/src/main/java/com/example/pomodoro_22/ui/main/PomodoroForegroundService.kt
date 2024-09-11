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

class PomodoroForegroundService : Service() {

    private var timer: CountDownTimer? = null
    private var timeLeftInMillis: Long = 0

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val action = intent?.action
        timeLeftInMillis = intent?.getLongExtra("time_left", 0) ?: 0

        when (action) {
            ACTION_START -> {
                createNotificationChannel()
                startForegroundService()
            }
            ACTION_STOP -> stopForegroundService()
        }

        return START_NOT_STICKY
    }


    // Check if notification permissions are granted (Android 13+)
    private fun checkNotificationPermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED
        } else {
            true
        }
    }

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

    private var completedWorkSessions: Int = 0

    private fun saveTimerStateToSharedPreferences() {
        val sharedPreferences = getSharedPreferences("pomodoro_prefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().apply {
            putLong("timeLeftInMillis", timeLeftInMillis)
            putString("currentPhase", PomodoroPhase.WORK.name)  // Save the current phase
            putBoolean("timerRunning", true)  // Indicate the timer is running
            putInt("completedWorkSessions", completedWorkSessions)  // Save completed work sessions
            apply()
        }
    }

    private fun startForegroundService() {
        val notification = createNotification(timeLeftInMillis)
        startForeground(NOTIFICATION_ID, notification)

        timer?.cancel()
        timer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                saveTimerStateToSharedPreferences() // Save the timer state
                updateNotification(millisUntilFinished)
            }

            override fun onFinish() {
                stopForegroundService()
            }
        }.start()
    }

    private fun stopForegroundService() {
        timer?.cancel()
        stopForeground(true)
        stopSelf()
    }

    private fun updateNotification(millisUntilFinished: Long) {
        val notification = createNotification(millisUntilFinished)
        if (checkNotificationPermission()) {
            NotificationManagerCompat.from(this).notify(NOTIFICATION_ID, notification)
        }
    }

    private fun createNotification(millisUntilFinished: Long): Notification {
        val minutes = (millisUntilFinished / 1000) / 60
        val seconds = (millisUntilFinished / 1000) % 60

        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE)

        return NotificationCompat.Builder(this, TIMER_CHANNEL_ID)
            .setContentTitle("Pomodoro Timer")
            .setContentText(String.format("%02d:%02d remaining", minutes, seconds))
            .setSmallIcon(R.drawable.timericon)  // Replace with an appropriate icon
            .setContentIntent(pendingIntent)
            .setOngoing(true)
            .setOnlyAlertOnce(true)
            .build()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    companion object {
        const val ACTION_START = "START"
        const val ACTION_STOP = "STOP"
        const val NOTIFICATION_ID = 1
        const val TIMER_CHANNEL_ID = "timer_channel"
    }
}