package com.example.pomodoro_22.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras

class MainViewModelFactory(
    private val application: Application,
    private val settingsViewModel: SettingsViewModel // Pass SettingsViewModel here
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(application, settingsViewModel) as T // Pass SettingsViewModel to MainViewModel
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
