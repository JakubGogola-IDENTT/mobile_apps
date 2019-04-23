package com.example.zad1

import java.io.*

class Controller(private val mainActivity: MainActivity, private val model: Model) {

    private val file: File = File(mainActivity.filesDir, "gallery.data")

    fun onRatingChange(position: Int, rating: Float) {
        val imageID = model.getImage(position).id
        model.setRating(position, rating)
        model.sortByRating()
        mainActivity.pointedIndex = model.imageList.indexOfFirst { it.id == imageID }
        mainActivity.viewAdapter.notifyDataSetChanged()
    }

    fun resetImageList(imageList: ArrayList<*>) {
        model.clearImageList()

        for (item in imageList) {
            val image = item as Image
            model.addImage(image.id, image.description, image.rating)
        }
    }

    fun saveData() {
        ObjectOutputStream(FileOutputStream(file)).use {
            it.writeObject(model.imageList)
        }
    }

    fun loadData() {
        if (!file.exists()) {
            return
        }

        ObjectInputStream(FileInputStream(file)).use {

            when (val loadedData = it.readObject()) {
                is ArrayList<*> -> {
                    addLoadedData(loadedData)
                }
                else -> println("Deserialization failed")
            }
        }
    }

    private fun addLoadedData(data: ArrayList<*>) {
        model.clearImageList()
        for (item in data) {
            val image = item as Image
            val index = model.containsImageWithID(image.id)

            if (index != -1) {
                model.updateImage(index, image)
            } else {
                model.addImage(image.id, image.description, image.rating)
            }
        }
    }
}