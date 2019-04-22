package com.example.zad1

class Model {
    val imageList: ArrayList<Image> = arrayListOf()

    fun addImage(url: String, description: String) {
        val image = Image(url, description, 0f)
        imageList.add(image)
    }

    fun removeImage(position: Int) {
        imageList.removeAt(position)
    }

    fun ratingSelector(image: Image): Float {
        return image.ratng
    }

    fun clearImageList() {
        imageList.clear()
    }
}