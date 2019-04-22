package com.example.zad1

class Model {
    val imageList: ArrayList<Image> = arrayListOf()

    fun loadImages(ids: ArrayList<Int>) {
        for (id in ids) {
            val image = Image(id, "XDDDD", 0f)
            imageList.add(image)
        }
    }

    fun addImage(id: Int, description: String, rating: Float) {
        val image = Image(id, description, rating)
        imageList.add(image)
    }

    fun removeImage(position: Int) {
        imageList.removeAt(position)
    }

    fun getImage(position: Int): Image {
        return if (position in imageList.indices) {
            imageList[position]
        } else {
            Image(R.drawable.ic_launcher_background, "", 0f)
        }
    }

    fun setRating(position: Int, rating: Float) {
        if (position in imageList.indices) {
            imageList[position].rating = rating
        }
    }

    fun clearImageList() {
        imageList.clear()
    }

    fun sortByrating() {
        imageList.sortByDescending { ratingSelector(it) }
    }

    private fun ratingSelector(image: Image): Float {
        return image.rating
    }
}