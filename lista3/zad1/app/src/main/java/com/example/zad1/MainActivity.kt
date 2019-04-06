package com.example.zad1

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

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
        startActivityForResult(addTaskIntent, 200)
    }

}
