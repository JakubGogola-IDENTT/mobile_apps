package com.example.zad1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import com.example.zad1.containers.Date
import com.example.zad1.containers.Task
import com.example.zad1.containers.Time
import com.example.zad1.enums.TaskPriority
import com.example.zad1.enums.TaskType
import com.example.zad1.pickers.DatePickerFragment
import com.example.zad1.pickers.TimePickerFragment

class AddTaskActivity : AppCompatActivity() {

    lateinit var time: Time
    lateinit var date: Date
    lateinit var taskName: String

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

        //Task priority spinner
        taskPrioritySpinner = findViewById(R.id.taskPrioritySpinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.task_priorites,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            taskPrioritySpinner.adapter = adapter
        }

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
        val newTask = Task(taskName, date, time, TaskType.HOME, TaskPriority.I)
        addTaskIntent.putExtra("newTask", newTask)
        setResult(Activity.RESULT_OK, addTaskIntent)
        finish()
    }
}