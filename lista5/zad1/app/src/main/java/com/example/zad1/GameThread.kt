package com.example.zad1

import android.graphics.Canvas
import android.view.SurfaceHolder
import kotlin.math.abs

class GameThread(private val surfaceHolder: SurfaceHolder, private val gameView: GameView) :
        Thread() {
    private var isRunning: Boolean = false
    private var canvas: Canvas? = null
    private val targetFPS : Int = 50

    fun setState(state: Boolean) {
        isRunning = state
    }

    override fun run() {
        var startTime : Long
        var timeMillis : Long
        var waitTime : Long
        val targetTime : Long = (1000 / targetFPS).toLong()

        while (isRunning) {
            startTime = System.nanoTime()
            canvas = null

            try {
                canvas = surfaceHolder.lockCanvas()
                synchronized(surfaceHolder) {
                    gameView.update()
                    if (canvas != null) {
                        gameView.draw(canvas)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }

            timeMillis = (System.nanoTime() - startTime) / 1000000
            waitTime = timeMillis - targetTime

            try {
                sleep(abs(waitTime))
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }
}