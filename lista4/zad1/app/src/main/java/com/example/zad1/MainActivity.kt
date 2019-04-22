package com.example.zad1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.zad1.adapters.ImagesListAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var model: Model
    private lateinit var imageListAdapter: ImagesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        model = Model()
        imageListAdapter = ImagesListAdapter(model.imageList)
    }

}
