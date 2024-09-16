package com.example.pomodoro_22.di

import com.example.pomodoro_22.repository.TaskRepository
import com.example.pomodoro_22.viewmodel.TaskViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    single { TaskRepository(androidContext()) }
    viewModel { TaskViewModel(get()) }
}
