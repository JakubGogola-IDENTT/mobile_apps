package com.example.zad1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Init retrofit
        retrofit = Retrofit.Builder().baseUrl("https://newton.now.sh")
            .addConverterFactory(GsonConverterFactory.create()).build()

    }
}
