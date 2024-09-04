package com.example.pomodoro_22.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.pomodoro_22.data.AppDatabase
import com.example.pomodoro_22.model.Task

class TaskRepository(context: Context) {

    private val taskDao = AppDatabase.getDatabase(context).taskDao()

    fun getAllTasks(): LiveData<List<Task>> = taskDao.getAllTasks()

    suspend fun addTask(task: Task) = taskDao.insertTask(task)

    suspend fun updateTask(task: Task) = taskDao.updateTask(task)

    suspend fun deleteTask(task: Task) = taskDao.deleteTask(task)
}

