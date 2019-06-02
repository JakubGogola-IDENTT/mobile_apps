package com.example.zad1

import android.text.format.DateFormat
import com.example.zad1.containers.Task
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

class Model : Serializable {

    var listOfTask: ArrayList<Task> = arrayListOf()

    fun addTask(task: Task) {
        listOfTask.add(task)
    }

    fun removeTaskAt(index: Int) {
        if (index < listOfTask.size) {
            listOfTask.removeAt(index)
        }
    }

    fun getTaskID(index: Int): Long {
        return if (index < listOfTask.size) {
            listOfTask[index].id
        } else {
            -1
        }
    }

    fun prioritySelector(task: Task): Int {
        return task.taskPriority.getPriorityIntValue()
    }

    fun typeSelector(task: Task): String {
        return task.taskType.type
    }

    fun nameSelector(task: Task): String {
        return task.taskName
    }

    fun dateSelector(task: Task): LocalDateTime {
        return LocalDateTime.of(
            task.date.year,
            task.date.month,
            task.date.day,
            task.time.hour,
            task.time.minute
        )
    }

    fun clearListOfTasks() {
        listOfTask.clear()
    }
}