package com.example.zad1

import com.example.zad1.containers.Task
import com.example.zad1.enums.SortType
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

    fun sort(desc: Boolean) {
        if (!desc) {
            when (mainActivity.sortType) {
                SortType.BY_NAME -> model.listOfTask.sortBy { model.nameSelector(it) }
                SortType.BY_TYPE -> model.listOfTask.sortBy { model.typeSelector(it) }
                SortType.BY_PRIORITY -> model.listOfTask.sortBy { model.prioritySelector(it) }
                SortType.BY_DATE -> model.listOfTask.sortBy { model.dateSelector(it) }
            }
        } else {
            when (mainActivity.sortType) {
                SortType.BY_NAME -> model.listOfTask.sortByDescending { model.nameSelector(it) }
                SortType.BY_TYPE -> model.listOfTask.sortByDescending { model.typeSelector(it) }
                SortType.BY_PRIORITY -> model.listOfTask.sortByDescending { model.prioritySelector(it) }
                SortType.BY_DATE -> model.listOfTask.sortByDescending { model.dateSelector(it) }
            }
        }
        mainActivity.toDoListAdapter.notifyDataSetChanged()
    }

    private fun addLoadedTasksToList(loadedData: ArrayList<*>) {
        for (task in loadedData) {
            model.addTask(task as Task)
        }
    }
}