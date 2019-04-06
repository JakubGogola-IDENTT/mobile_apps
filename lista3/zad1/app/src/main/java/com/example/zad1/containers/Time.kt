package com.example.zad1.containers

import java.io.Serializable

data class Time(val hour: Int, val minute: Int) : Serializable{
    override fun toString(): String {
        return "$hour:$minute"
    }
}