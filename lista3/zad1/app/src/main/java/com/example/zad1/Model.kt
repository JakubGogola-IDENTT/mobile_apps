package com.example.zad1

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

    fun clearListOfTasks() {
        listOfTask.clear()
    }

}