package com.example.zad1

class Controller(private val mainActivity: MainActivity, private val model: Model) {
    fun onRatingChange(position: Int, rating: Float) {
        model.setRating(position, rating)
        model.sortByrating()
        mainActivity.imageListAdapter.notifyDataSetChanged()
    }
}