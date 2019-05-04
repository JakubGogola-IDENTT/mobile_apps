package com.example.zad1

import android.graphics.Paint

class Ball(private val initX: Float, private val initY: Float, val size: Float,
           color: Paint) {
    var x = initX
    var y = initY
    val color = color

    fun setXPos(dx: Float) {
        x += dx
    }

    fun setYPos(dy: Float) {
        y += dy
    }

    fun moveBall(newX: Float, newY: Float) {
        x = newX
        y = newY
    }

    fun resetBall() {
        x = initX
        y = initY
    }
}