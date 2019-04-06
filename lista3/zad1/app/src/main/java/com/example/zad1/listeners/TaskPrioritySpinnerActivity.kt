package com.example.zad1.listeners

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.example.zad1.AddTaskActivity
import com.example.zad1.R
import com.example.zad1.enums.TaskPriority

class TaskPrioritySpinnerActivity(private val addTaskActivity: AddTaskActivity) :
    Activity(), AdapterView.OnItemSelectedListener {


    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (parent != null) {
            addTaskActivity.taskPriority = TaskPriority.valueOf(parent.getItemAtPosition(position).toString())
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        addTaskActivity.taskPriority = TaskPriority.I
    }
}