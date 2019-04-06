package com.example.zad1.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.zad1.R
import com.example.zad1.containers.Task
import com.example.zad1.enums.TaskType

class ToDoListAdapter(private val ctx: Context, private val data: ArrayList<Task>) :
    ArrayAdapter<Task>(ctx, 0, data) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            val inflater = LayoutInflater.from(ctx)
            view = inflater.inflate(R.layout.item_layout, parent, false)
        }
        val task = data[position]

        view!!.findViewById<TextView>(R.id.taskName).text = task.taskName

        val iconID = getIconID(task.taskType)
        view.findViewById<ImageView>(R.id.taskImage).setImageResource(iconID)

        view.findViewById<TextView>(R.id.taskDate).text = task.date.toString()
        view.findViewById<TextView>(R.id.taskTime).text = task.time.toString()
        view.findViewById<TextView>(R.id.taskPriority).text = task.taskPriority.toString()

        return view
    }

    private fun getIconID(taskType: TaskType): Int {
        return when (taskType) {
            TaskType.STUDIES -> R.drawable.diary
            TaskType.HOME -> R.drawable.home_icon
            TaskType.WORK -> R.drawable.briefcase
            TaskType.HOBBY -> R.drawable.games
            TaskType.OTHER -> R.drawable.toolbox
        }
    }
}