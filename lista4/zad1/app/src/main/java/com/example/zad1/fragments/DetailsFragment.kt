package com.example.zad1.fragments

import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.zad1.Image
import com.example.zad1.MainActivity
import com.example.zad1.R
import kotlinx.android.synthetic.main.details_fragment.*

class DetailsFragment : Fragment() {
    private var index: Int = 0
    private lateinit var image: Image

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (arguments != null) {
            image = arguments!!.get("image") as Image
            index = arguments!!.get("index") as Int
        }

        image_rating.rating = image.rating

        image_rating.setOnRatingBarChangeListener { _, rating, _ ->
            this.image.rating = rating

            if (activity?.resources?.configuration?.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                (activity as MainActivity).controller.onRatingChange(
                    index,
                    rating
                )
            }
        }
    }

    companion object {
        fun newInstance(index: Int, image: Image): DetailsFragment {
            val fragment = DetailsFragment()
            val args = Bundle()
            args.putInt("index", index)
            args.putSerializable("image", image)
            fragment.arguments = args

            return fragment
        }
    }
}