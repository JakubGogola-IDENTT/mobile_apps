package com.example.zad1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
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
        var x = (width / 2.0).toFloat()
        var y = (height / 2.0).toFloat()
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

    fun update() {
        model.updateBallPosition()
        model.checkCollision()
    }
}