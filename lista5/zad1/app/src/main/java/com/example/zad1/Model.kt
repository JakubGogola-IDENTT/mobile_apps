package com.example.zad1

import android.graphics.Paint

class Model(private val gameView: GameView) {
    // Ball instance
    val ball: Ball
    val leftPlayer: Player
    val rightPlayer: Player

    // Deltas for ball
    private var dx = 5f
    private var dy = 5f

    init {
        // Ball initialization
        val ballSize = 100f
        val color = Paint()
        color.setARGB(255, 255, 0, 0)
        ball = Ball(0f, 0f, ballSize, ballSize, color)

        // Player initialization
        val playerWidth = 50f
        val playerHeight = 300f
        val playerColor = Paint()
        playerColor.setARGB(255, 255, 255, 255)
        leftPlayer = Player(0f, 0f, playerWidth, playerHeight, playerColor)
        rightPlayer = Player(0f, 0f, playerWidth, playerHeight, playerColor)
    }

    fun updateBallPosition() {
        ball.setXPos(dx)
        ball.setYPos(dy)

        if (ball.x <= 0 || ball.right >= gameView.width) {
            dx = -dx
        }

        if (ball.y <= 0 || ball.bottom >= gameView.height) {
            dy = -dy
        }
    }

    fun checkCollision() {
        val player: Player = when {
            checkCollisionWithPlayer(leftPlayer) -> leftPlayer
            checkCollisionWithPlayer(rightPlayer) -> rightPlayer
            else -> return
        }

        //Change dx sign
        dx = -dx

        val hitPosition = player.bottom - ball.top
        var delta = 4f
        val bound = player.height.toInt() - 30

        for (pos in bound downTo 0 step 30) {
            if (hitPosition >= pos && hitPosition <= pos + 30) {
                dy = delta
                break
            } else {
                delta--
            }
        }
    }

    // It's fucking tricky
    private fun checkCollisionWithPlayer(player: Player): Boolean {
        return when {
            ball.left > player.right -> false
            ball.right < player.left -> false
            ball.top > player.bottom -> false
            else -> ball.bottom >= player.top
        }
    }
}