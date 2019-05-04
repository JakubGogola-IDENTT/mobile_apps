package com.example.zad1

import android.graphics.Paint
import android.graphics.RectF

abstract class Object(private val initX: Float, private val initY: Float,
                      private val initWidth: Float, private val initHeight: Float,
                      initColor: Paint) {
    var x = initX
    var y = initY
    var width = initWidth
    var height = initHeight
    val color = initColor
    lateinit var rect: RectF

    var left: Float = x
    var top: Float = y
    var right: Float = x + width
    var bottom: Float = y + height


    fun setXPos(dx: Float) {
        x += dx
        left += dx
        right += dx
    }

    fun setYPos(dy: Float) {
        y += dy
        top += dy
        bottom += dy
    }

    fun moveToPos(newX: Float, newY: Float) {
        x = newX
        y = newY
        setPosAttributes()
    }

    private fun setPosAttributes() {
        left = x
        top = y
        right = x + width
        bottom = y + height
    }

    fun reset() {
        x = initX
        y = initY
        width = initWidth
        height = initHeight
    }

    fun getReactF(): RectF {
        rect = RectF(left, top, right, bottom)
        return rect
    }
}