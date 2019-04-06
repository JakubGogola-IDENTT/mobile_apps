package com.example.zad1.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.zad1.containers.Task

class ToDoListAdapter(private val ctx: Context, private val data: ArrayList<Task>) :
    ArrayAdapter<Task>(ctx, 0, data) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return super.getView(position, convertView, parent)
    }

}