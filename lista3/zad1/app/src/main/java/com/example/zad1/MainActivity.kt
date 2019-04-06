package com.example.zad1

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import com.example.zad1.adapters.ToDoListAdapter
import com.example.zad1.containers.Task

class MainActivity : AppCompatActivity() {

    var model = Model()

    private lateinit var controller: Controller
    private lateinit var listView: ListView
    private lateinit var toDoListAdapter: ToDoListAdapter
    private lateinit var taskTypesArray: Array<String>
    private lateinit var taskPrioritiesArray: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        controller  = Controller(this, model)

        toDoListAdapter = ToDoListAdapter(this, model.listOfTask)
        listView = findViewById(R.id.tasksList)
        listView.adapter = toDoListAdapter

        taskTypesArray = resources.getStringArray(R.array.task_types)
        taskPrioritiesArray = resources.getStringArray(R.array.task_priorities)

        listView.setOnItemLongClickListener { _, _, position, _ ->
            model.removeTaskAt(position)
            toDoListAdapter.notifyDataSetChanged()
            true
        }
    }

    fun onAddTaskButtonClick(view: View) {
        val addTaskIntent = Intent(this, AddTaskActivity::class.java)
        addTaskIntent.putExtra("taskTypesArray", taskTypesArray)
        addTaskIntent.putExtra("taskPrioritiesArray", taskPrioritiesArray)
        startActivityForResult(addTaskIntent, 200)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 200) {
            val task = data?.getSerializableExtra("newTask") as Task
            model.addTask(task)
            toDoListAdapter.notifyDataSetChanged()
        }
    }
}
