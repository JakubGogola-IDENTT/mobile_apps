package com.example.zad1

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.zad1.adapters.ImagesListAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var model: Model
    lateinit var imageListAdapter: ImagesListAdapter
    lateinit var controller: Controller

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //get IDs
        val imageIDs: ArrayList<Int> = ArrayList()
        for (i in 1..11) {
            val id = resources.getIdentifier("img_$i", "drawable", packageName)
            println("id: $id")
            imageIDs.add(id)
        }
        model = Model()
        model.loadImages(imageIDs)

        controller = Controller(this, model)
        imageListAdapter = ImagesListAdapter(model.imageList)

        viewManager = LinearLayoutManager(this)
        viewAdapter = ImagesListAdapter(model.imageList)
        recyclerView = findViewById<RecyclerView>(R.id.image_list).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }



}
