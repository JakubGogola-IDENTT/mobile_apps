package com.example.zad1.listeners

import android.app.Activity
import android.view.View
import android.widget.AdapterView
import com.example.zad1.MainActivity
import com.example.zad1.enums.SortType

class SortSpinnerActivity(private val mainActivity: MainActivity) :
    Activity(), AdapterView.OnItemSelectedListener {

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (parent != null) {
            val type = parent.getItemAtPosition(position).toString()

            for (v in SortType.values()) {
                if (v.type == type) {
                    mainActivity.sortType = v
                }
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        mainActivity.sortType = SortType.BY_NAME
    }
}