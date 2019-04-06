package com.example.zad1

import com.example.zad1.containers.Task
import java.io.*

class Controller(private  val mainActivity: MainActivity, private val model: Model) {

    private val file = File(mainActivity.filesDir, "tasks_list.data")

    fun saveList() {
        ObjectOutputStream(FileOutputStream(file)).use { it ->
            it.writeObject(model.listOfTask)
        }
    }

    fun loadList() {
        if (!file.exists()) {
            return
        }

        ObjectInputStream(FileInputStream(file)).use { it ->
            val loadedData = it.readObject()

            when (loadedData) {
                is ArrayList<*> -> {
                    addLoadedTasksToList(loadedData as ArrayList<*>)
                }
                else -> println("Deserialization failed")
            }
        }
    }

    private fun addLoadedTasksToList(loadedData: ArrayList<*>) {
        for (task in loadedData) {
            model.addTask(task as Task)
        }
    }
}