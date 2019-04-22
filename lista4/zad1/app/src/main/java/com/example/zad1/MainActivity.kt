package com.example.zad1

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.zad1.adapters.ImagesListAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var model: Model
    private lateinit var imageListAdapter: ImagesListAdapter

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewManager = LinearLayoutManager(this)
        viewAdapter = ImagesListAdapter(model.imageList)

        model = Model()
        imageListAdapter = ImagesListAdapter(model.imageList)

        //TODO: Loading images
    }


}
