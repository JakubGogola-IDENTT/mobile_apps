package com.example.zad1.pickers

import android.annotation.SuppressLint
import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.text.format.DateFormat
import android.widget.EditText
import android.widget.TimePicker
import com.example.zad1.AddTaskActivity
import com.example.zad1.R
import com.example.zad1.containers.Time
import java.util.*

@SuppressLint("ValidFragment")
class TimePickerFragment(private val addTaskActivity: AddTaskActivity) :
    DialogFragment(), TimePickerDialog.OnTimeSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        return TimePickerDialog(activity, this, hour, minute, DateFormat.is24HourFormat(activity))
    }

    @SuppressLint("SetTextI18n")
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        addTaskActivity.time = Time(hourOfDay, minute)
        //taskTime.setText("$hourOfDay:$minute")
        addTaskActivity.findViewById<EditText>(R.id.taskTime).setText("$hourOfDay:$minute")
    }
}