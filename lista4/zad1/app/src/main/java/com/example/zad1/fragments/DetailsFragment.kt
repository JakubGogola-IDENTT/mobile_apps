package com.example.zad1.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.zad1.Image
import com.example.zad1.R

class DetailsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
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