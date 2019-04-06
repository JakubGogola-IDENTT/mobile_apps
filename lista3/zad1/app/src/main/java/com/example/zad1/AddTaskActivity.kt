package com.example.zad1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.*
import com.example.zad1.containers.Date
import com.example.zad1.containers.Task
import com.example.zad1.containers.Time
import com.example.zad1.enums.TaskPriority
import com.example.zad1.enums.TaskType
import com.example.zad1.listeners.TaskPrioritySpinnerActivity
import com.example.zad1.listeners.TaskTypeSpinnerActivity
import com.example.zad1.pickers.DatePickerFragment
import com.example.zad1.pickers.TimePickerFragment

class AddTaskActivity : AppCompatActivity() {

    lateinit var time: Time
    lateinit var date: Date
    lateinit var taskName: String
    lateinit var taskType: TaskType
    lateinit var taskPriority: TaskPriority

    lateinit var taskTypeSpinner: Spinner
    lateinit var taskPrioritySpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_task_activity)

        //Task type spinner adapter
        taskTypeSpinner = findViewById(R.id.taskTypeSpinner)

        ArrayAdapter.createFromResource(
            this,
            R.array.task_types,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            taskTypeSpinner.adapter = adapter
        }
        taskTypeSpinner.onItemSelectedListener = TaskTypeSpinnerActivity(this)

        //Task priority spinner
        taskPrioritySpinner = findViewById(R.id.taskPrioritySpinner)

        ArrayAdapter.createFromResource(
            this,
            R.array.task_priorities,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            taskPrioritySpinner.adapter = adapter
        }
        taskPrioritySpinner.onItemSelectedListener = TaskPrioritySpinnerActivity(this)
    }

    fun onTimeButtonClick(view:View) {
        TimePickerFragment(this).show(supportFragmentManager, "timePicker")
    }

    fun onDateButtonClick(view: View) {
        DatePickerFragment(this).show(supportFragmentManager, "datePicker")
    }

    fun onAddTaskClick(view: View) {
        val addTaskIntent = Intent()
        taskName = findViewById<EditText>(R.id.taskName).text.toString()
        println(date)
        println(time)
        println(taskType)
        println(taskPriority)
        val newTask = Task(taskName, date, time, taskType, taskPriority)
        addTaskIntent.putExtra("newTask", newTask)
        setResult(Activity.RESULT_OK, addTaskIntent)
        finish()
    }
}