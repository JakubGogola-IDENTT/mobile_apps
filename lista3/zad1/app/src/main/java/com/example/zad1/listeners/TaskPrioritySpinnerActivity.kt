package com.example.zad1.listeners

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.example.zad1.AddTaskActivity
import com.example.zad1.R
import com.example.zad1.enums.TaskPriority
import com.example.zad1.enums.TaskType

class TaskPrioritySpinnerActivity(private val addTaskActivity: AddTaskActivity) :
    Activity(), AdapterView.OnItemSelectedListener {

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            if (parent != null) {
                val priority = parent.getItemAtPosition(position).toString()

                for (v in TaskPriority.values()) {
                    if (v.priority == priority) {
                        addTaskActivity.taskPriority = v
                        return
                    }
                }
            }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        addTaskActivity.taskPriority = TaskPriority.I
    }
}