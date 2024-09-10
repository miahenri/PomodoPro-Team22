package com.example.pomodoro_22.ui.main

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

enum class PomodoroPhase {
    WORK, SHORT_BREAK, LONG_BREAK
}

class MainViewModel : ViewModel() {

    private var timer: CountDownTimer? = null
    private val _timeLeftInMillis = MutableStateFlow(25 * 60 * 1000L) // Initial time for work phase
    val timeLeftInMillis = _timeLeftInMillis.asStateFlow()

    private val _timerRunning = MutableStateFlow(false)
    val timerRunning = _timerRunning.asStateFlow()

    private val _phase = MutableStateFlow(PomodoroPhase.WORK) // Track current phase
    val phase = _phase.asStateFlow()

    private var workSessionCount = 0 // Count how many work sessions have been completed

    // This property returns the total time of the active phase (work, short break, long break)
    val totalTimeForCurrentPhase: Long
        get() = when (_phase.value) {
            PomodoroPhase.WORK -> 25 * 60 * 1000L
            PomodoroPhase.SHORT_BREAK -> 5 * 60 * 1000L
            PomodoroPhase.LONG_BREAK -> 50 * 60 * 1000L
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
}