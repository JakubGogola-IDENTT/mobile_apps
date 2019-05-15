package com.example.zad1

import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow

class ButtonsGenerator(private val mainActivity: MainActivity) {

    var buttons: MutableMap<Int, Operation> = mutableMapOf()

    init {
        val tableLayout = mainActivity.findViewById<TableLayout>(R.id.buttons_layout) as TableLayout
        var buttonID = 100
        val operations = Operation.values()
        var index = 0

        for (i in 0..2) {
            val tableRow = TableRow(mainActivity)
            tableRow.layoutParams = TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT, 1f)
            tableLayout.addView(tableRow)
            for (i in 0..4) {
                val button = Button(mainActivity)
                button.layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.MATCH_PARENT, 1f)
                button.textSize = 11f
                button.text = operations[index].toString()
                button.id = buttonID
                buttons[buttonID] = operations[index]
                button.setOnClickListener {
                    mainActivity.onOperationsButtonClick(button)
                }
                tableRow.addView(button)
                index++
                buttonID++
            }
        }

    }

}