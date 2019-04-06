package com.example.zad1.listeners

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.example.zad1.AddTaskActivity
import com.example.zad1.R
import com.example.zad1.enums.TaskType

class TaskTypeSpinnerActivity(private val addTaskActivity: AddTaskActivity, private val res: Array<String>) :
    Activity(), AdapterView.OnItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        addTaskActivity.taskType = TaskType.HOME
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        addTaskActivity.taskType = TaskType.OTHER
    }
}