package com.example.zad1.enums

enum class TaskPriority(val priority: String) : TaskPriorityInterface {
    I("I") {
        override fun getPriorityIntValue(): Int {
            return 1
        }
    },
    II("II") {
        override fun getPriorityIntValue(): Int {
            return 2
        }
    },
    III("III") {
        override fun getPriorityIntValue(): Int {
            return 3
        }
    },
    IV("IV") {
        override fun getPriorityIntValue(): Int {
            return 4
        }
    },
    V("V") {
        override fun getPriorityIntValue(): Int {
            return 5
        }
    }
}