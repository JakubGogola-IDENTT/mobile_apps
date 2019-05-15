package com.example.zad1

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var retrofit: Retrofit
    private lateinit var newtonAPI: NewtonAPI
    private lateinit var buttonsGenerator: ButtonsGenerator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonsGenerator = ButtonsGenerator(this)
        val resetButton = findViewById<Button>(R.id.reset_button)
        resetButton.setOnClickListener {
            onResetButtonClick(resetButton)
        }

        // Init retrofit
        retrofit = Retrofit.Builder().baseUrl("https://newton.now.sh")
            .addConverterFactory(GsonConverterFactory.create()).build()
        newtonAPI = retrofit.create(NewtonAPI::class.java)

    }

    fun onOperationsButtonClick(view: View) {
        val operation = buttonsGenerator.buttons[view.id].toString()
        val expression = buildExpression(view.id)

        operation.let { newtonAPI.getResponse(it, expression) }.enqueue(object : Callback<NewtonDTO> {
            val result = findViewById<TextView>(R.id.result)

            @SuppressLint("SetTextI18n")
            override fun onFailure(call: Call<NewtonDTO>, t: Throwable) {
                result.setTextColor(Color.parseColor("#ff0000"))
                result.text = "Something doesn't work :--("
            }

            override fun onResponse(call: Call<NewtonDTO>, response: Response<NewtonDTO>) {
                val body = response.body()
                if (body != null) {
                    if (body.error != null) {
                        result.setTextColor(Color.parseColor("#ff0000"))
                        result.text = body.error.toString()
                    } else {
                        result.setTextColor(Color.parseColor("#000000"))
                        result.text = body.result.toString()
                    }
                }
            }
        })
    }

    private fun onResetButtonClick(view: View) {
        findViewById<TextView>(R.id.expression_input).text = ""
        findViewById<TextView>(R.id.result).text = ""
        findViewById<TextView>(R.id.first_arg).text = ""
        findViewById<TextView>(R.id.second_arg).text = ""
    }


    private fun buildExpression(id: Int): String  {
        val operation = buttonsGenerator.buttons[id]
        val firstArg = findViewById<TextView>(R.id.first_arg).text.toString()
        val secondArg = findViewById<TextView>(R.id.second_arg).text.toString()
        val expression = findViewById<TextView>(R.id.expression_input).text.toString()

        return when (operation) {
            Operation.TANGENT -> "$firstArg|$expression"
            Operation.LOG -> "$firstArg|$expression"
            Operation.AREA -> "$firstArg:$secondArg|$expression"
            else -> expression
        }
    }
}
