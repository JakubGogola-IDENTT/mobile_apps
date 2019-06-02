package com.example.zad1.containers

import com.example.zad1.enums.TaskPriority
import com.example.zad1.enums.TaskType
import java.io.Serializable

data class Task(val taskName: String, val date: Date, val time: Time, val taskType: TaskType,
                val taskPriority: TaskPriority, var id: Long) : Serializable