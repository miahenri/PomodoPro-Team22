package com.example.pomodoro_22.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import android.os.CountDownTimer
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import com.example.pomodoro_22.service.PomodoroForegroundService

enum class PomodoroPhase {
    WORK, SHORT_BREAK, LONG_BREAK
}

class MainViewModel(application: Application, private val settingsViewModel: SettingsViewModel) : AndroidViewModel(application) {

    private val sharedPreferences: SharedPreferences = application.getSharedPreferences("pomodoro_prefs", Context.MODE_PRIVATE)
    private val context: Context = application.applicationContext

    private val _phase = MutableLiveData<PomodoroPhase>(getPhaseFromPreferences())
    val phase: MutableLiveData<PomodoroPhase> = _phase

    private val _timeLeftInMillis = MutableLiveData<Long>(getTimeFromPreferences())
    val timeLeftInMillis: MutableLiveData<Long> = _timeLeftInMillis

    private val _timerRunning = MutableLiveData<Boolean>(sharedPreferences.getBoolean("timer_running", false))
    val timerRunning: MutableLiveData<Boolean> = _timerRunning

    private var timer: CountDownTimer? = null
    private var completedWorkSessions = sharedPreferences.getInt("completed_work_sessions", 0)

    init {
        restoreTimerState()
    }

    val totalTimeForCurrentPhase: Long
        get() = when (_phase.value) {
            PomodoroPhase.WORK -> settingsViewModel.workTimeMinutes.value * 60 * 1000L
            PomodoroPhase.SHORT_BREAK -> settingsViewModel.shortBreakTimeMinutes.value * 60 * 1000L
            PomodoroPhase.LONG_BREAK -> settingsViewModel.longBreakTimeMinutes.value * 60 * 1000L
            else -> 0L
        }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun checkAndRequestNotificationPermission(activity: Activity) {
        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 1)
        }
    }

    fun startTimer() {
        timer?.cancel() // Timer im ViewModel gestartet
        _timerRunning.value = true
        startCountdownTimer(_timeLeftInMillis.value ?: totalTimeForCurrentPhase) // Sicherstellung nicht-null Werte
    }

    fun stopTimer() {
        timer?.cancel() // Timer im ViewModel gestoppt
        _timerRunning.value = false
        stopForegroundService()
    }

    fun resetTimer() {
        timer?.cancel() // Timer im ViewModel zurückgesetzt
        _phase.value = PomodoroPhase.WORK
        _timeLeftInMillis.value = totalTimeForCurrentPhase
        _timerRunning.value = false
        completedWorkSessions = 0
        stopForegroundService()
        saveTimerStateToSharedPreferences()
    }

    fun skipPhase() {
        timer?.cancel()
        transitionToNextPhase() // Phasewechsel im ViewModel
        _timeLeftInMillis.value = totalTimeForCurrentPhase
        stopForegroundService()
        sendServiceCommand(PomodoroForegroundService.ACTION_SKIP)
        startTimer() // Timer nach Phasewechsel neu gestartet
    }

    private fun startCountdownTimer(initialMillis: Long) { // StartCountdownTimer im ViewModel
        timer = object : CountDownTimer(initialMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _timeLeftInMillis.value = millisUntilFinished
                saveTimerStateToSharedPreferences()
                startForegroundService(millisUntilFinished, _phase.value ?: PomodoroPhase.WORK)
            }

            override fun onFinish() {
                _timeLeftInMillis.value = 0L
                _timerRunning.value = false
                skipPhase()
            }
        }.start()
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
            null -> return
        }
        saveTimerStateToSharedPreferences()
    }

    private fun getPhaseFromPreferences(): PomodoroPhase {
        val phaseName = sharedPreferences.getString("current_phase", PomodoroPhase.WORK.name)
        return PomodoroPhase.valueOf(phaseName ?: PomodoroPhase.WORK.name)
    }

    private fun getTimeFromPreferences(): Long {
        return sharedPreferences.getLong("time_left", settingsViewModel.workTimeMinutes.value * 60 * 1000L)
    }

    private fun sendServiceCommand(action: String) {
        val intent = Intent(context, PomodoroForegroundService::class.java).apply {
            this.action = action
        }
        ContextCompat.startForegroundService(context, intent)
    }

    private fun startForegroundService(timeLeftInMillis: Long, currentPhase: PomodoroPhase) {
        val intent = Intent(context, PomodoroForegroundService::class.java).apply {
            action = PomodoroForegroundService.ACTION_START
            putExtra("time_left", timeLeftInMillis)
            putExtra("current_phase", currentPhase.name)
        }
        ContextCompat.startForegroundService(context, intent)
    }

    private fun stopForegroundService() {
        val intent = Intent(context, PomodoroForegroundService::class.java).apply {
            action = PomodoroForegroundService.ACTION_STOP
        }
        context.startService(intent)
    }

    fun restoreTimerState() {
        _timeLeftInMillis.value = getTimeFromPreferences()
        _phase.value = getPhaseFromPreferences()
        _timerRunning.value = sharedPreferences.getBoolean("timer_running", false)

        if (_timerRunning.value == true) {
            startTimer()
        }
    }

    private fun saveTimerStateToSharedPreferences() {
        sharedPreferences.edit().apply {
            putLong("time_left", _timeLeftInMillis.value ?: 0L) // Sicherstellung nicht-null Werte
            putString("current_phase", _phase.value?.name ?: PomodoroPhase.WORK.name) // Sicherstellung nicht-null Werte
            putBoolean("timer_running", _timerRunning.value == true)
            putInt("completed_work_sessions", completedWorkSessions)
            apply()
        }
    }
}