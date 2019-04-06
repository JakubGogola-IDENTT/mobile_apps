package com.example.zad1

import android.text.format.DateFormat
import com.example.zad1.containers.Task

class Model {

    var listOfTask: ArrayList<Task> = arrayListOf()

    fun addTask(task: Task) {
        listOfTask.add(task)
    }

    fun removeTaskAt(index: Int) {
        if (index < listOfTask.size) {
            listOfTask.removeAt(index)
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

    fun clearListOfTasks() {
        listOfTask.clear()
    }
}