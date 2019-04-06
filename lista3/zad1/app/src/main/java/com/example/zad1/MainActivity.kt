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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        controller  = Controller(this, model)

        toDoListAdapter = ToDoListAdapter(this, model.listOfTask)
        listView = findViewById(R.id.tasksList)
        listView.adapter = toDoListAdapter

    }

    fun onAddTaskButtonClick(view: View) {
        val addTaskIntent = Intent(this, AddTaskActivity::class.java)
        startActivityForResult(addTaskIntent, 200)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 200) {
            val task = data?.getSerializableExtra("newTask") as Task
            model.addTask(task)
            println(model.listOfTask.size)
            toDoListAdapter.notifyDataSetChanged()
        }
    }
}
