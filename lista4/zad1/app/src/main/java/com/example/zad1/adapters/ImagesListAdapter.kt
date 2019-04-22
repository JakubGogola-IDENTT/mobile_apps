package com.example.zad1.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.zad1.Image
import com.example.zad1.MainActivity
import com.example.zad1.R
import kotlinx.android.synthetic.main.image_list_item.view.*

class ImagesListAdapter(private val imageList: ArrayList<Image>) :
    RecyclerView.Adapter<ImagesListAdapter.ViewHolder>() {

    private var context: Context? = null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        context = p0.context
        val view = LayoutInflater.from(p0.context).inflate(R.layout.image_list_item, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val image = imageList[p0.adapterPosition]
        println("position: ${p0.adapterPosition}")
        println("id: ${image.id}")
        p0.view.item_image.setImageResource(image.id)
        p0.view.image_rating.rating = image.rating

        p0.view.setOnClickListener {
            (context as MainActivity).onDetailsActivityRender(p0.adapterPosition)
        }
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}