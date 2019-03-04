package com.example.zad1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {

    private var points = -1
    private var num1 = 0
    private var num2 = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        roll(true)
    }

    fun roll(f: Boolean) {
        if ( (num1 >= num2 && f) || (num2 >= num1 && !f)) {
            points++
            Toast.makeText(this, "Dobrze!", Toast.LENGTH_SHORT).show()

        }
        else {
            points--
            Toast.makeText(this, "Źle!", Toast.LENGTH_SHORT).show()
        }

        findViewById<TextView>(R.id.textView2).text = "Punkty: $points"

        val r = Random()
        num1 = r.nextInt(10)
        num2 = r.nextInt(10)

        findViewById<Button>(R.id.button).text = "$num1"
        findViewById<Button>(R.id.button2).text = "$num2"

    }

    fun leftClick(view: View) {
        roll(true)

    }

    fun rightClick(view: View) {
        roll(false)
    }

}
