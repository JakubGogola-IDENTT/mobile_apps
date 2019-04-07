package com.example.zad1.containers

import java.io.Serializable

data class Time(val hour: Int, val minute: Int) : Serializable{
    override fun toString(): String {
        var hourString = hour.toString()
        var minuteString = minute.toString()

        if (hour < 10) {
            hourString = "0$hourString"
        }

        if (minute < 10) {
            minuteString = "0$minuteString"
        }

        return "$hourString:$minuteString"
    }
}