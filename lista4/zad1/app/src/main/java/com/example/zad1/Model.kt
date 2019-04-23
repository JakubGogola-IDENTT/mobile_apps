package com.example.zad1

import kotlin.random.Random

class Model(private val mainActivity: MainActivity) {
    private val descriptions: Array<String> = mainActivity.resources.getStringArray(R.array.descriptions)
    val imageList: ArrayList<Image> = arrayListOf()

    fun loadImages(ids: ArrayList<Int>) {
        for (id in ids) {
            val description = getRandomDescription()
            val image = Image(id, description, 0f)
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

    fun containsImageWithID(id: Int): Int {
        imageList.forEachIndexed { index, element ->
            if (element.id == id) {
                return index
            }
        }

        return -1
    }

    fun clearImageList() {
        imageList.clear()
    }

    fun updateImage(index: Int, image: Image) {
        imageList[index].rating = image.rating
        imageList[index].description = image.description
    }

    fun sortByRating() {
        imageList.sortByDescending { ratingSelector(it) }
    }

    private fun ratingSelector(image: Image): Float {
        return image.rating
    }

    private fun getRandomDescription(): String {
        val size = descriptions.size
        val r = Random.nextInt(0, size)
        return descriptions[r]
    }
}