package com.example.pomodoro_22.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pomodoro_22.data.local.dao.TaskDao
import com.example.pomodoro_22.data.local.model.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}