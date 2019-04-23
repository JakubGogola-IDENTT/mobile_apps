package com.example.zad1.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.zad1.Image
import com.example.zad1.R
import com.example.zad1.fragments.DetailsFragment

class DetailsActivity : AppCompatActivity() {
    private lateinit var detailsFragment: DetailsFragment
    private lateinit var image: Image
    private var index: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_activity)

        image = intent.getSerializableExtra("image") as Image
        index = intent.getIntExtra("index", 0)

        if (savedInstanceState != null) {
            image = savedInstanceState.getSerializable("image") as Image
            index = savedInstanceState.getInt("index")
        }

        detailsFragment = DetailsFragment.newInstance(index, image)
        supportFragmentManager.beginTransaction().add(R.id.fragment_frame, detailsFragment).commit()
    }


    override fun onSaveInstanceState(outState: Bundle?) {
        if (outState != null) {
            outState.putSerializable("image", image)
            outState.putInt("index", index)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onBackPressed() {
        val intent = Intent()
        intent.putExtra("image", detailsFragment.image)
        intent.putExtra("index", index)
        setResult(Activity.RESULT_OK, intent)
        finish()
        super.onBackPressed()
    }
}