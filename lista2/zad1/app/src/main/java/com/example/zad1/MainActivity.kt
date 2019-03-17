package com.example.zad1

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var model = Model()

    private lateinit var buttons: Buttons
    lateinit var controller: Controller

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttons = Buttons()
        controller = Controller(model, buttons, this)
    }


    @SuppressLint("SetTextI18n")
    fun onButtonClick(view: View) {
        controller.markField(view.id)
    }

}
