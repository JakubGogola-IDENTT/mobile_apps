package com.example.zad1

class Model {
    val imageList: ArrayList<Image> = arrayListOf()

    fun loadImages(urls: Array<String>) {
        //TODO: Implement images loading
    }

    fun addImage(url: String, description: String) {
        val image = Image(url, description, 0f)
        imageList.add(image)
    }

    fun removeImage(position: Int) {
        imageList.removeAt(position)
    }

    fun clearImageList() {
        imageList.clear()
    }

    fun sortByrating() {
        imageList.sortBy { ratingSelector(it) }
    }

    private fun ratingSelector(image: Image): Float {
        return image.ratng
    }
}