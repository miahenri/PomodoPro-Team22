package com.example.pomodoro_22.data.repository

import com.example.pomodoro_22.data.local.dao.TaskDao
import com.example.pomodoro_22.data.local.model.Task
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: TaskDao) {

    val allTasks: Flow<List<Task>> = taskDao.getAllTasks()

    suspend fun insert(task: Task) {
        taskDao.insert(task)
    }

    suspend fun delete(task: Task) {
        taskDao.delete(task)
    }
}