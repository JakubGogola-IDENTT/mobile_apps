package com.example.zad1

import android.graphics.Paint

class Model(private val gameView: GameView) {
    // Ball instance
    val ball: Ball

    // Deltas
    private var dx = 5f
    private var dy = 5f

    init {
        val initX = (gameView.width / 2.0).toFloat()
        val initY = (gameView.height / 2.0).toFloat()
        val ballSize = 100f
        val color = Paint()
        color.setARGB(255, 255, 0, 0)

        // TODO: change to proper values
        ball = Ball(initX, initY, ballSize, color)
    }

    fun updateBallPosition() {
        ball.setXPos(dx)
        ball.setYPos(dy)

        if (ball.x <= 0 || ball.x + ball.size >= gameView.width) {
            dx = -dx
        }

        if (ball.y <= 0 || ball.y + ball.size >= gameView.height) {
            dy = -dy
        }
    }
}