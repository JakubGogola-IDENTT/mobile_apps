package com.example.zad1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var retrofit: Retrofit
    private lateinit var buttonsGenerator: ButtonsGenerator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonsGenerator = ButtonsGenerator(this)

        // Init retrofit
        retrofit = Retrofit.Builder().baseUrl("https://newton.now.sh")
            .addConverterFactory(GsonConverterFactory.create()).build()

        println(Operation.SIMPLIFY)

    }
}
