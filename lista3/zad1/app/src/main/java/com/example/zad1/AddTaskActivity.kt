package com.example.zad1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView

@SuppressLint("Registered")
class AddTaskActivity : AppCompatActivity() {

    lateinit var time: Time
    lateinit var date: Date

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_task_activity)
    }

    fun onTimeButtonClick(view:View) {
        TimePickerFragment().show(supportFragmentManager, "timePicker")
    }

    fun onDateButtonClick(view: View) {
        DatePickerFragment().show(supportFragmentManager, "datePicker")
    }

    fun onAddTaskClick(view: View) {
        val addTaskIntent = Intent()
    }

}