package com.example.zad1

import android.os.AsyncTask
import com.example.zad1.containers.Date
import com.example.zad1.containers.Task
import com.example.zad1.containers.Time
import com.example.zad1.database.DBTask
import com.example.zad1.enums.SortType
import com.example.zad1.enums.TaskPriority
import com.example.zad1.enums.TaskType
import java.io.*
import java.lang.Exception

class Controller(private  val mainActivity: MainActivity, private val model: Model) {

    private val file = File(mainActivity.filesDir, "tasks_list.data")

    fun loadList() {
        val database = mainActivity.database
        val loadedFromDatabase = database.taskDAO().getAllTasks()
        println(loadedFromDatabase)
        val tasks = ArrayList<Task>()

        for (item in loadedFromDatabase) {
            val date = Date(item.day, item.month, item.year)
            val time = Time(item.hour, item.minute)
            val type = getType(item.taskType)
            val priority = getPriority(item.taskPriority)
            val task = Task(item.taskName, date, time, type, priority, item.id)
            tasks.add(task)
        }

        mainActivity.runOnUiThread {
            addLoadedTasksToList(tasks)
        }
    }

    fun sort(desc: Boolean) {
        if (!desc) {
            when (mainActivity.sortType) {
                SortType.BY_NAME -> model.listOfTask.sortBy { it.taskName }
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

    fun addTaskToDatabase(task: Task) {
        AsyncTask.execute {
            try {
                val database = mainActivity.database
                val dbTask = DBTask(taskName = task.taskName, day = task.date.day, month = task.date.month,
                    year = task.date.year, hour = task.time.hour, minute = task.time.minute,
                    taskType = task.taskType.toString(), taskPriority = task.taskPriority.toString())
                database.taskDAO().insertAll(dbTask)
                val id = database.taskDAO().getLastID()
                task.id = id

                val result = database.taskDAO().getAllTasks()
                println(result)

                mainActivity.runOnUiThread {
                    model.addTask(task)
                    mainActivity.toDoListAdapter.notifyDataSetChanged()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun removeTaskFromDatabase(id: Long, position: Int) {
        AsyncTask.execute {
            try {
                val database = mainActivity.database
                database.taskDAO().deleteTask(id)

                val result = database.taskDAO().getAllTasks()
                println(result)

                mainActivity.runOnUiThread {
                    model.removeTaskAt(position)
                    mainActivity.toDoListAdapter.notifyDataSetChanged()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun addLoadedTasksToList(loadedData: ArrayList<*>) {
        for (task in loadedData) {
            model.addTask(task as Task)
        }
    }

    private fun getType(typeString: String): TaskType {
        val types = TaskType.values()

        for (t in types) {
            if (typeString == t.toString()) {
                return t
            }
        }

        return  TaskType.OTHER
    }

    private fun getPriority(priorityString: String): TaskPriority {
        val priorities = TaskPriority.values()

        for (p in priorities) {
            if (priorityString == p.toString()) {
                return p
            }
        }

        return TaskPriority.I
    }
}