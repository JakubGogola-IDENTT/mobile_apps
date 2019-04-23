package com.example.zad1

class Controller(private val mainActivity: MainActivity, private val model: Model) {
    fun onRatingChange(position: Int, rating: Float) {
        val imageID = model.getImage(position).id
        println("onratingpos: $position")
        model.setRating(position, rating)
        model.sortByRating()
        mainActivity.pointedIndex = model.imageList.indexOfFirst { it.id == imageID }
        mainActivity.imageListAdapter.notifyDataSetChanged()
    }

    fun resetImageList(imageList: ArrayList<Image>) {
        model.clearImageList()

        for (image in imageList) {
            model.addImage(image.id, image.description, image.rating)
        }
    }
}