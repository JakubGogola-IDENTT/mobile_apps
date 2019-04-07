package com.example.zad1.containers

import java.io.Serializable

data class Date(val day: Int, val month: Int, val year: Int) : Serializable {
    override fun toString(): String {
        var dayString = day.toString()
        var monthString = month.toString()

        if (day < 10) {
            dayString = "0$dayString"
        }

        if (month < 10) {
            monthString = "0$monthString"
        }

        return "$dayString.$monthString.$year"
    }
}