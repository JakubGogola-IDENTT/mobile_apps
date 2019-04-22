package com.example.zad1.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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
        p0.bind()
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind() {
            val image = imageList[adapterPosition]
            view.item_image.setImageResource(image.id)

//            view.setOnClickListener {
//                (context as MainActivity).laun
//            }
        }
    }
}