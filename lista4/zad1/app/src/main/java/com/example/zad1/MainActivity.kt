package com.example.zad1

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.zad1.activities.DetailsActivity
import com.example.zad1.adapters.ImagesListAdapter
import com.example.zad1.fragments.DetailsFragment

class MainActivity : AppCompatActivity() {
    private lateinit var model: Model
    lateinit var imageListAdapter: ImagesListAdapter
    lateinit var controller: Controller

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var detailsFragment: DetailsFragment = DetailsFragment()

    var pointedIndex: Int = 0

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

        model = Model(this)

        controller = Controller(this, model)
        imageListAdapter = ImagesListAdapter(model.imageList)

        viewManager = LinearLayoutManager(this)
        viewAdapter = ImagesListAdapter(model.imageList)
        recyclerView = findViewById<RecyclerView>(R.id.image_list).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        if (savedInstanceState != null) {
            val images = savedInstanceState.getSerializable("images") as ArrayList<*>

            controller.resetImageList(images as ArrayList<Image>)

            pointedIndex = savedInstanceState.getInt("index", 0)
        } else {
            //Loading images from resources
            model.loadImages(imageIDs)
        }

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            onDetailsFragmentRender(pointedIndex)
        }

    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        if (outState != null) {
            outState.putSerializable("images", model.imageList)
            outState.putInt("index", pointedIndex)
        }
    }

    private fun onDetailsFragmentRender(index: Int) {
        supportFragmentManager.beginTransaction().remove(detailsFragment).commit()
        println("pointed: $pointedIndex")
        detailsFragment = DetailsFragment.newInstance(index, model.getImage(index))
        supportFragmentManager.beginTransaction().add(R.id.fragment_frame, detailsFragment).commit()
    }

    fun onDetailsActivityRender(index: Int) {
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("index", index)
            intent.putExtra("image", model.getImage(index))
            startActivityForResult(intent, 2137)
        } else {
            onDetailsFragmentRender(index)
        }

        pointedIndex = index
        println("pointed: $pointedIndex")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val index: Int

        if (requestCode == 2137) {
            if (resultCode == Activity.RESULT_OK) {

                if (data != null) {
                    index = data.getIntExtra("index", 0)

                    val image = data.getSerializableExtra("image") as Image

                    controller.onRatingChange(index, image.rating)
                    println("Rating set")
                }
            }
        }
    }

}
