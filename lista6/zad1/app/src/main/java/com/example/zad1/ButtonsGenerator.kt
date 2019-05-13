package com.example.zad1

import android.widget.TableLayout

class ButtonsGenerator(private val mainActivity: MainActivity) {

    init {
        val tableLayout = mainActivity.findViewById<TableLayout>(R.id.buttons_layout) as TableLayout
        var buttonID = 100

    }

}