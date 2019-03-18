package com.example.zad1

import android.view.View
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import com.example.zad1.R

class Buttons(private var mainActivity: MainActivity) {
    var buttonIDs: MutableMap<Int, Pair<Int, Int>> = mutableMapOf()

    init {
        val tableLayout = mainActivity.findViewById<TableLayout>(R.id.tableLayout) as TableLayout
        var id = 100

        for (i in 0..4) {
            val tableRow = TableRow(mainActivity)
            tableRow.layoutParams = TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT, 1f)
            tableLayout.addView(tableRow)

            for (j in 0..4) {
                val button = Button(mainActivity)
                button.layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.MATCH_PARENT, 1f)
                button.text = ""
                button.id = id
                button.setOnClickListener {
                    mainActivity.onFieldButtonClick(button)
                }
                tableRow.addView(button)
                buttonIDs[button.id] = Pair(i, j)
                id++
            }
        }
    }
}