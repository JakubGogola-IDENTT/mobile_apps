package com.example.zad1

class Model {
    val imageList: ArrayList<Image> = arrayListOf()

    fun loadImages(ids: ArrayList<Int>) {
        for (id in ids) {
            val image = Image(id, "", 0f)
            imageList.add(image)
        }
    }

    fun addImage(id: Int, description: String) {
        val image = Image(id, description, 0f)
        imageList.add(image)
    }

    fun removeImage(position: Int) {
        imageList.removeAt(position)
    }

    fun getImage(position: Int): Image? {
        return if (position in imageList.indices) {
            imageList[position]
        } else {
            null
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
        imageList.sortBy { ratingSelector(it) }
    }

    private fun ratingSelector(image: Image): Float {
        return image.rating
    }
}