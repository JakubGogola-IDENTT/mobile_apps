package com.example.zad2

import android.annotation.SuppressLint
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class Controller(private var model: Model, private var mainActivity: MainActivity) {

    private val image = mainActivity.findViewById<ImageView>(R.id.hangmanImage)

    init {
        initWord()
    }

    private fun initWord() {
        var word = ""

        for (c in model.currentWord) {
            word += "?"
        }

        image.setImageResource(R.drawable.hangman_0)
        mainActivity.findViewById<TextView>(R.id.word).text = word
        mainActivity.findViewById<TextView>(R.id.letters).text = ""
        mainActivity.findViewById<EditText>(R.id.inputText).text.clear()
    }

    @SuppressLint("SetTextI18n")
    fun checkInput() {
        val input = mainActivity.findViewById<EditText>(R.id.inputText).text

        if (input.isNotEmpty() && !model.endGame) {
            val c = input.first().toLowerCase()
            val result = model.checkLetter(c)

            if (result) {
                putGuessedLetters()
                if (model.hasWon()) {
                    mainActivity.findViewById<TextView>(R.id.word).text = "You won: ${model.currentWord}"
                }
            } else {
                changeImage()
                putIncorrectLetters()
                if (model.endGame) {
                    mainActivity.findViewById<TextView>(R.id.word).text = "You lost :--("
                }
            }
        }

        mainActivity.findViewById<EditText>(R.id.inputText).text.clear()
    }

    private fun putGuessedLetters() {
        var word = ""

        for (c in model.currentWord.toLowerCase()) {
            if (model.guessedLetters.contains(c)) {
                word += c
            } else {
                word += "?"
            }
        }

        mainActivity.findViewById<TextView>(R.id.word).text = word
    }


    private fun putIncorrectLetters() {
        var wrongLetters = ""

        for (c in model.incorrectLetters) {
            wrongLetters += c
        }

        mainActivity.findViewById<TextView>(R.id.letters).text = wrongLetters
    }

    private fun changeImage() {
        when (model.step) {
            0 -> image.setImageResource(R.drawable.hangman_0)
            1 -> image.setImageResource(R.drawable.hangman_1)
            2 -> image.setImageResource(R.drawable.hangman_2)
            3 -> image.setImageResource(R.drawable.hangman_3)
            4 -> image.setImageResource(R.drawable.hangman_4)
            5 -> image.setImageResource(R.drawable.hangman_5)
            6 -> image.setImageResource(R.drawable.hangman_6)
            7 -> image.setImageResource(R.drawable.hangman_7)
            8 -> image.setImageResource(R.drawable.hangman_8)
            9 -> image.setImageResource(R.drawable.hangman_9)
            10 -> image.setImageResource(R.drawable.hangman_10)
            11 -> image.setImageResource(R.drawable.hangman_11)
            12 -> image.setImageResource(R.drawable.hangman_12)
        }
    }

    fun reset() {
        model.reset()
        initWord()
    }
}