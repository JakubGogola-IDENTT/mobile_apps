package com.example.zad1

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.zad1.containers.Task

class MainActivity : AppCompatActivity() {

    var model = Model()

    private lateinit var controller: Controller

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        controller  = Controller(this, model)

    }

    fun onAddTaskButtonClick(view: View) {
        val addTaskIntent = Intent(this, AddTaskActivity::class.java)
        startActivityForResult(addTaskIntent, Activity.RESULT_OK)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == Activity.RESULT_OK) {
            val task = data?.getSerializableExtra("newTask") as Task
            model.addTask(task)
        }
    }

}
