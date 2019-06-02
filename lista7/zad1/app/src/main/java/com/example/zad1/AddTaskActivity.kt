package com.example.zad1

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
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
import java.util.*

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

        //DBTask type spinner adapter
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

        //DBTask priority spinner
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

        setDefaultDateAndTime()
    }

    fun onTimeButtonClick(view:View) {
        TimePickerFragment(this).show(supportFragmentManager, "timePicker")
    }

    fun onDateButtonClick(view: View) {
        DatePickerFragment(this).show(supportFragmentManager, "datePicker")
    }

    override fun onBackPressed() {
        super.onBackPressed()
        println(Activity.RESULT_CANCELED)
        val addTaskIntent = Intent()
        setResult(Activity.RESULT_CANCELED, addTaskIntent)
        finish()
    }

    fun onAddTaskClick(view: View) {
        taskName = findViewById<EditText>(R.id.taskName).text.toString()

        if (taskName.trim().isEmpty()) {
            val builder: AlertDialog.Builder? = this.let {
                AlertDialog.Builder(it)
            }

            builder?.setMessage(R.string.alert_empty_task_name)!!.setTitle(R.string.alert_title)

            val dialog: AlertDialog? = builder.create()
            dialog!!.show()
            return
        }

        val addTaskIntent = Intent()
        println(date)
        println(time)
        println(taskType)
        println(taskPriority)
        val newTask = Task(taskName, date, time, taskType, taskPriority, -1)
        addTaskIntent.putExtra("newTask", newTask)
        setResult(Activity.RESULT_OK, addTaskIntent)
        finish()
    }

    @SuppressLint("SetTextI18n")
    private fun setDefaultDateAndTime() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH) + 1
        val day = c.get(Calendar.DAY_OF_MONTH)
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)
        date = Date(day, month, year)
        time = Time(hour, minute)
        findViewById<EditText>(R.id.taskDate).setText(date.toString())
        findViewById<EditText>(R.id.taskTime).setText(time.toString())
    }

}