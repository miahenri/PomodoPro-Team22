package com.example.pomodoro_22.ui.task

import android.util.Log
import androidx.lifecycle.*
import com.example.pomodoro_22.model.Task
import com.example.pomodoro_22.repository.TaskRepository
import kotlinx.coroutines.launch

class TaskViewModel(private val taskRepository: TaskRepository) : ViewModel() {

    val tasks: LiveData<List<Task>> = taskRepository.getAllTasks()

    init {
        Log.d("TaskViewModel", "TaskViewModel initialized")
    }

    fun addTask(task: Task) {
        Log.d("TaskViewModel", "Adding new task: ${task.id}")
        viewModelScope.launch {
            taskRepository.addTask(task)
        }
    }

    fun updateTask(task: Task) {
        Log.d("TaskViewModel", "Updating task: ${task.id}")
        viewModelScope.launch {
            taskRepository.updateTask(task)
        }
    }

    fun deleteTask(task: Task) {
        Log.d("TaskViewModel", "Deleting task: ${task.id}")
        viewModelScope.launch {
            taskRepository.deleteTask(task)
        }
    }
}
