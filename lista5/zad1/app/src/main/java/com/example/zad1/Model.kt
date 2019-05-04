package com.example.zad1

class Model(private val gameView: GameView) {
    // Ball instance
    // TODO: change to proper values
    private val ball = Ball(0f, 0f)

    // Deltas
    private var dx = 5f
    private var dy = 5f

    private val size = 300f

    fun updateBallPosition() {
        ball.setXPos(dx)
        ball.setYPos(dy)

        if (ball.x <= 0 || ball.x + size >= gameView.width) {
            dx = -dx
        }

        if (ball.y <= 0 || ball.y + size >= gameView.height) {
            dy = -dy
        }
    }

    fun loadBallPosition(initX: Float, initY: Float) {

    }
}