package com.example.zad1

class Model {

    var listOfTask: MutableList<Task> = mutableListOf()

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