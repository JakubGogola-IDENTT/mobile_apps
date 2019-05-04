package com.example.zad1

import android.graphics.Paint
import android.graphics.RectF

class Ball(private val initX: Float, private val initY: Float, private val initWidth: Float,
             private val initHeight: Float, private val initColor: Paint) :
        Object (initX, initY, initWidth, initHeight, initColor) {
    var size: Float = width
}