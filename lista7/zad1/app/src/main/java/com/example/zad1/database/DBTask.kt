package com.example.zad1.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.example.zad1.enums.TaskPriority
import java.sql.Time
import java.util.*

@Entity(tableName = "tasks")
data class DBTask(
    @ColumnInfo(name = "taskName") var taskName: String,
    @ColumnInfo(name = "day") var day: Int,
    @ColumnInfo(name = "month") var month: Int,
    @ColumnInfo(name = "year") var year: Int,
    @ColumnInfo(name = "hour") var hour: Int,
    @ColumnInfo(name = "minute") var minute: Int,
    @ColumnInfo(name = "taskType") var taskType: String,
    @ColumnInfo(name = "taskPriority") var taskPriority: String,
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true) var id: Long = 0
)