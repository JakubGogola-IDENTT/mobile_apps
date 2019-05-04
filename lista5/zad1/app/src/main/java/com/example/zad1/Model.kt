package com.example.zad1

import android.graphics.Paint
import com.example.zad1.PlayerSymbol.*
import kotlin.math.abs

class Model(private val gameView: GameView) {
    // Ball instance
    val ball: Ball
    val leftPlayer: Player
    val rightPlayer: Player

    private val playersMap: Map<PlayerSymbol, Player>
    private var currentPlayer: PlayerSymbol = RIGHT

    // Deltas for ball
    var dx = 8f
    var dy = 8f

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
        leftPlayer = Player(0f, 0f, playerWidth, playerHeight, playerColor, gameView)
        rightPlayer = Player(0f, 0f, playerWidth, playerHeight, playerColor, gameView)
        playersMap = mapOf(Pair(LEFT, leftPlayer), Pair(RIGHT, rightPlayer))
    }

    fun updateBallPosition() {
        ball.setXPos(dx)
        ball.setYPos(dy)

        if (ball.x <= 0 || ball.right >= gameView.width) {
            resetDeltas()
            ball.reset()
            ball.moveToPos(gameView.width / 2f, gameView.height / 2f)
            return
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

        // Change speed of ball
        if (dx <= 0) {
            dx -= 1f
        } else {
            dx += 1f
        }

        //Change dx sign
        dx = -dx

        //val hitPosition = player.bottom - ball.top
        val hitPosition = abs(player.top - ball.getMiddleYCoordinate())
        when (hitPosition) {
            in 0f..30f -> dy = -8f
            in 30f..60f -> dy = -6f
            in 60f..90f -> dy = -4f
            in 90f..120f -> dy = -2f
            in 120f..150f -> dy = 0f
            in 150f..180f -> dy = 2f
            in 180f..210f -> dy = 4f
            in 210f..240f -> dy = 6f
            in 240f..270f -> dy = 8f
            in 270f..300f -> dy = 9f
        }
    }

    private fun checkCollisionWithPlayer(player: Player): Boolean {
        return when {
            ball.left > player.right -> false
            ball.right < player.left -> false
            ball.top > player.bottom -> false
            else -> ball.bottom >= player.top
        }
    }

    private fun resetDeltas() {
        dx = 8f
        dy = 8f
        currentPlayer = when {
            ball.x <= 0 -> RIGHT
            ball.x >= gameView.height -> LEFT
            else -> RIGHT
        }

        if (currentPlayer == LEFT) {
            dx = -dx
        }
    }
}