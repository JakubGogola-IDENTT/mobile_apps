package com.example.zad1

class Ball(private val initX: Float, private val initY: Float) {
    var x = initX
    var y = initY

    fun setXPos(dx: Float) {
        x += dx
    }

    fun setYPos(dy: Float) {
        y += dy
    }

    fun resetBall() {
        x = initX
        y = initY
    }
}