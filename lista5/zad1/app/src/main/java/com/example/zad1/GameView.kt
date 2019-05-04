package com.example.zad1

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView

class GameView (context: Context, attributeSet: AttributeSet) :
        SurfaceView (context, attributeSet), SurfaceHolder.Callback {

    private val model: Model = Model(this)
    private val ball = model.ball
    private val leftPlayer = model.leftPlayer
    private val rightPlayer = model.rightPlayer
    private val gameThread : GameThread

    init {
        holder.addCallback(this)
        gameThread = GameThread(holder, this)
    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
        // Probably useless function
        return
    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
        gameThread.setState(false)
        gameThread.join()
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        var x = width / 2f
        val y = height / 2f
        ball.moveToPos(x, y)

        x = width.toFloat()
        rightPlayer.moveToPos(x - rightPlayer.width, 0f)

        gameThread.setState(true)
        gameThread.start()
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)

        if (canvas == null) {
            return
        }

        canvas.drawOval(ball.getReactF(), ball.color)
        canvas.drawRect(leftPlayer.getReactF(), leftPlayer.color)
        canvas.drawRect(rightPlayer.getReactF(), rightPlayer.color)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
//        return super.onTouchEvent(event)
        if (event == null) {
            return false
        }

        val x = event.x
        val y = event.y

        val centerWidth = width / 2f
        val centerHeight = height / 2f
        val delta = height / 30f

        when {
            x < centerWidth && y < centerHeight -> leftPlayer.setYPos(-delta)
            x < centerWidth && y >= centerHeight -> leftPlayer.setYPos(delta)
            x > centerWidth && y < centerHeight -> rightPlayer.setYPos(-delta)
            x > centerWidth && y >= centerHeight -> rightPlayer.setYPos(delta)
        }

        return true
    }

    fun update() {
        model.updateBallPosition()
        model.checkCollision()
    }
}