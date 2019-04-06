package com.example.zad1.listeners

import android.app.Activity
import android.view.View
import android.widget.AdapterView
import com.example.zad1.AddTaskActivity
import com.example.zad1.R
import com.example.zad1.enums.TaskPriority

class TaskPrioritySpinnerActivity(private val addTaskActivity: AddTaskActivity) :
    Activity(), AdapterView.OnItemSelectedListener {

    private val arr = resources.getStringArray(R.array.task_priorites)

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        addTaskActivity.taskPriority = TaskPriority.valueOf(arr[position])
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        addTaskActivity.taskPriority = TaskPriority.I
    }
}