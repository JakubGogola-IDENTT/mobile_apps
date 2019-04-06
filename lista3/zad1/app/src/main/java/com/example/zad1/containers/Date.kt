package com.example.zad1.containers

import java.io.Serializable

data class Date(val day: Int, val month: Int, val year: Int) : Serializable {
    override fun toString(): String {
        return "$day.$month.$year"
    }
}