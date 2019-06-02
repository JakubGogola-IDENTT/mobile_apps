package com.example.zad1.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database( entities = [(DBTask::class)], version = 1 )
abstract class ToDoDatabase : RoomDatabase() {
    abstract fun taskDAO(): TaskDAO
}