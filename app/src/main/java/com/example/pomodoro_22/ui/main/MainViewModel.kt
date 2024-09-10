package com.example.pomodoro_22.ui.main

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {

    private var timer: CountDownTimer? = null
    private val _timeLeftInMillis = MutableStateFlow(25 * 60 * 1000L) // Startzeit in Millisekunden
    val timeLeftInMillis = _timeLeftInMillis.asStateFlow()

    private val _timerRunning = MutableStateFlow(false)
    val timerRunning = _timerRunning.asStateFlow()

    fun startTimer() {
        timer?.cancel()
        timer = object : CountDownTimer(_timeLeftInMillis.value, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _timeLeftInMillis.value = millisUntilFinished
            }

            override fun onFinish() {
                _timeLeftInMillis.value = 0L
                _timerRunning.value = false
            }
        }.start()
        _timerRunning.value = true
    }

    fun stopTimer() {
        timer?.cancel()
        _timerRunning.value = false
    }

    fun resetTimer() {
        timer?.cancel()
        _timeLeftInMillis.value = 25 * 60 * 1000L
        _timerRunning.value = false
    }
}
