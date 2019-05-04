package com.example.zad1

import android.graphics.Paint

class Ball(initX: Float, initY: Float, initWidth: Float, initHeight: Float, initColor: Paint) :
        Object (initX, initY, initWidth, initHeight, initColor)  {
    fun getMiddleYCoordinate(): Float {
        return bottom - height / 2f
    }
}