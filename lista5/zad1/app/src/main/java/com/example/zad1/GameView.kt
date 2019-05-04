package com.example.zad1

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView

class GameView (context: Context, attributeSet: AttributeSet) :
        SurfaceView (context, attributeSet), SurfaceHolder.Callback {

    private val model: Model = Model(this)
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
        gameThread.setState(true)
        gameThread.start()
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
    }

    fun update() {

    }
}