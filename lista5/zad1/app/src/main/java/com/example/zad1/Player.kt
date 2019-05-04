package com.example.zad1

import android.graphics.Paint

class Player(private val initX: Float, private val initY: Float, private val initWidth: Float,
             private val initHeight: Float, private val initColor: Paint,
             private val gameView: GameView) :
        Object (initX, initY, initWidth, initHeight, initColor) {

    override fun setYPos(dy: Float) {
        val deltaBottom = bottom + dy
        val deltaTop = top + dy

        when {
            deltaTop <= 0f -> moveToPos(x, 0f)
            deltaBottom >= gameView.height -> moveToPos(x, gameView.height - height)
            else -> super.setYPos(dy)
        }
    }
}