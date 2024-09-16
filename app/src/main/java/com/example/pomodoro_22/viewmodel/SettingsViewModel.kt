package com.example.pomodoro_22.viewmodel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SettingsViewModel(application: Application) : AndroidViewModel(application) {
    private val sharedPreferences: SharedPreferences = application.getSharedPreferences("pomodoro_prefs", Context.MODE_PRIVATE)

    // MutableStateFlows for the settings
    private val _workSessionCount = MutableStateFlow(4)  // Default: 4 sessions
    val workSessionCount = _workSessionCount.asStateFlow()

    private val _workTimeMinutes = MutableStateFlow(25)  // Default: 25 minutes
    val workTimeMinutes = _workTimeMinutes.asStateFlow()

    private val _shortBreakTimeMinutes = MutableStateFlow(5)  // Default: 5 minutes
    val shortBreakTimeMinutes = _shortBreakTimeMinutes.asStateFlow()

    private val _longBreakTimeMinutes = MutableStateFlow(15)  // Default: 15 minutes
    val longBreakTimeMinutes = _longBreakTimeMinutes.asStateFlow()

    init {
        loadSettings()
    }

    // Load settings from SharedPreferences
    private fun loadSettings() {
        _workSessionCount.value = sharedPreferences.getInt("workSessionCount", 4)
        _workTimeMinutes.value = sharedPreferences.getInt("workTimeMinutes", 25)
        _shortBreakTimeMinutes.value = sharedPreferences.getInt("shortBreakTimeMinutes", 5)
        _longBreakTimeMinutes.value = sharedPreferences.getInt("longBreakTimeMinutes", 15)
    }

    // Save settings to SharedPreferences
    fun saveSettings(workSessionCount: Int, workTimeMinutes: Int, shortBreakTimeMinutes: Int, longBreakTimeMinutes: Int) {
        sharedPreferences.edit().apply {
            putInt("workSessionCount", workSessionCount)
            putInt("workTimeMinutes", workTimeMinutes)
            putInt("shortBreakTimeMinutes", shortBreakTimeMinutes)
            putInt("longBreakTimeMinutes", longBreakTimeMinutes)
            apply()
        }

        _workSessionCount.value = workSessionCount
        _workTimeMinutes.value = workTimeMinutes
        _shortBreakTimeMinutes.value = shortBreakTimeMinutes
        _longBreakTimeMinutes.value = longBreakTimeMinutes
    }
}