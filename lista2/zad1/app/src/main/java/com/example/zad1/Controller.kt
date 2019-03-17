package com.example.zad1

import com.example.zad1.R
import android.annotation.SuppressLint
import android.widget.Button
import android.widget.TextView

class Controller(private var model: Model, private var buttons: Buttons, private var mainActivity: MainActivity) {

    @SuppressLint("SetTextI18n")
    fun markField(id: Int) {
        val row = buttons.buttonIDs[id]!!.first
        val column = buttons.buttonIDs[id]!!.second

        val symbol = model.markField(row, column)

        when (symbol) {
            Symbol.X -> mainActivity.findViewById<Button>(id).text = "X"
            Symbol.O -> mainActivity.findViewById<Button>(id).text = "O"
            Symbol.EMPTY -> mainActivity.findViewById<TextView>(R.id.errorText).text = "Invalid move!"
        }

        val hasAnyoneWon =  model.check()

        if (hasAnyoneWon) {
            mainActivity.findViewById<TextView>(R.id.errorText).text = "WON!!!!!!!!!!!"
        }
    }

    fun resetFields() {
        model.reset()

        for ((k, _) in buttons.buttonIDs) {
            mainActivity.findViewById<Button>(k).text = ""
        }

        mainActivity.findViewById<TextView>(R.id.errorText).text = ""
    }
}