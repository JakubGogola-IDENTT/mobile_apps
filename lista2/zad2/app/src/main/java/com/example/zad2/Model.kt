package com.example.zad2

import kotlin.random.Random

class Model(mainActivity: MainActivity) {
    private val words: Array<String> = mainActivity.resources.getStringArray(R.array.words_array)

    var incorrectLetters: MutableSet<Char> = mutableSetOf()

    var guessedLetters: MutableSet<Char> = mutableSetOf()

    var currentWord: String = ""

    private var wordLettersNum: Int = 0

    var step = 0

    var endGame = false

    init {
        currentWord = nextWord()
    }

    fun checkLetter(c: Char): Boolean {
        return if (c in currentWord.toLowerCase()) {
            guessedLetters.add(c)
            true
        } else {
            if (!incorrectLetters.contains(c)) {
                step++
                incorrectLetters.add(c)

                if (step == 12) {
                    endGame = true
                }
            }

            false
        }
    }

    fun hasWon(): Boolean {
        return if (wordLettersNum == guessedLetters.size && !endGame) {
            endGame = true
            true
        } else {
            false
        }
    }

    private fun nextWord(): String {
        val r = Random.nextInt(0, words.size - 1)
        currentWord = words[r]

        val wordLettersSet: MutableSet<Char> = mutableSetOf()

        for (c in currentWord.toLowerCase()) {
            wordLettersSet.add(c)
        }

        wordLettersNum = wordLettersSet.size

        return currentWord
    }

    fun reset() {
        step = 0
        endGame = false
        incorrectLetters.clear()
        guessedLetters.clear()
        nextWord()
    }
}