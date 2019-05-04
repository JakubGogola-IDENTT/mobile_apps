package com.example.zad1

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
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
    private val PREFS_NAME = "pong_data"
    lateinit var sharedPreferences: SharedPreferences


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

        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putFloat("ball_x", ball.x)
        editor.putFloat("ball_y", ball.y)
        editor.putFloat("dx", model.dx)
        editor.putFloat("dy", model.dy)
        editor.putFloat("left_x", leftPlayer.x)
        editor.putFloat("left_y", leftPlayer.y)
        editor.putFloat("right_x", rightPlayer.x)
        editor.putFloat("right_y", rightPlayer.y)
        editor.apply()
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        // Ball
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        var x = sharedPreferences.getFloat("ball_x", width / 2f)
        var y = sharedPreferences.getFloat("ball_y", height / 2f)
        ball.moveToPos(x, y)

        // Deltas
        model.dx = sharedPreferences.getFloat("dx", 8f)
        model.dy = sharedPreferences.getFloat("dy", 8f)

        // Left player
        x = sharedPreferences.getFloat("left_x", 0f)
        y = sharedPreferences.getFloat("left_y", 0f)
        leftPlayer.moveToPos(x, y)

        // Right player
        x = sharedPreferences.getFloat("right_x", width.toFloat() - rightPlayer.width)
        y = sharedPreferences.getFloat("right_y", 0f)

        rightPlayer.moveToPos(x, y)

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

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        super.onTouchEvent(event)
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