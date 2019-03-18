package com.example.zad2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    private lateinit var  model: Model
    private lateinit var controller: Controller

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        model = Model(this)
        controller = Controller(model, this)
    }

    fun onCheckButtonClick(view: View) {
        controller.checkInput()
    }

    fun onResetButtonClick(view: View) {
        controller.reset()
    }
}
