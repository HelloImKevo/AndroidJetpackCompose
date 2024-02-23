package com.schanz.todolist.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.schanz.todolist.data.models.ToDoTask

@Database(entities = [ToDoTask::class], version = 1, exportSchema = false)
abstract class ToDoDatabase : RoomDatabase() {

    abstract fun toDoDao(): ToDoDao
}
