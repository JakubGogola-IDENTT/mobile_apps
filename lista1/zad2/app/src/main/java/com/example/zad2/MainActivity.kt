package com.example.zad2

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.example.zad2.Logic

class MainActivity : AppCompatActivity() {

    private val logic = Logic()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        logic.nextNumber()
        this.setNumbers()
    }

    private fun setNumbers() {
        findViewById<TextView>(R.id.lowerNum).text = logic.lowerNum.toString()
        findViewById<TextView>(R.id.higherNum).text = logic.higherNum.toString()
    }

    @SuppressLint("SetTextI18n")
    fun checkButton(view: View) {
        val typedText: String = findViewById<EditText>(R.id.answer).text.toString()

        if (typedText != "") {
            val answer = typedText.toInt()
            val result = logic.check(answer)

            if(result) {
                findViewById<TextView>(R.id.result).text = "Correct!"
                findViewById<TextView>(R.id.hint).text = ""
                findViewById<TextView>(R.id.points).text = "Points: ${logic.totalPoints}"
                logic.nextNumber()
                this.setNumbers()
            } else {
                val order = logic.checkOrder(answer)
                findViewById<TextView>(R.id.result).text = "Wrong! Number is $order than your answer"
            }
        }

        findViewById<EditText>(R.id.answer).text.clear()
    }

    fun restartButton(view: View) {
        logic.restart()
        this.setNumbers()
        findViewById<TextView>(R.id.result).text = ""
        findViewById<TextView>(R.id.hint).text = ""
    }

    @SuppressLint("SetTextI18n")
    fun hintButton(view: View) {
        val hint = logic.getHint()
        findViewById<TextView>(R.id.hint).text = "Number is divisible by $hint"
    }
}
