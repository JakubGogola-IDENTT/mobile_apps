package com.example.zad1.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface TaskDAO {
    @Query("SELECT * FROM tasks")
    fun getAllTasks() : List<DBTask>

    @Insert
    fun insertAll(vararg dbTask: DBTask)

    @Query("SELECT MAX(id) FROM tasks")
    fun getLastID() : Long

    @Query("DELETE FROM tasks WHERE id = :id")
    fun deleteTask(id: Long) : Int

    @Query("DELETE FROM tasks")
    fun deleteAll()
}