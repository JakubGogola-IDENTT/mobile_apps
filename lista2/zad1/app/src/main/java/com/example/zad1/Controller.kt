package com.example.zad1

import com.example.zad1.R
import android.annotation.SuppressLint
import android.widget.Button
import android.widget.TextView

class Controller(private var model: Model, private var buttons: Buttons, private var mainActivity: MainActivity) {

    private var isBotModeOn: Boolean = false

    private val bot: Bot = Bot(model, buttons)

    @SuppressLint("SetTextI18n")
    fun markField(id: Int) {
        val row = buttons.buttonIDs[id]!!.first
        val column = buttons.buttonIDs[id]!!.second

        val symbol = model.markField(row, column)

        putSymbol(id, symbol)

        var hasAnyoneWon = model.check()

        if (hasAnyoneWon) {
            mainActivity.findViewById<TextView>(R.id.infoText).text =
                "${model.playersSymbols[model.winner]} has won!"
        } else if (!model.gameEnd) {
            mainActivity.findViewById<TextView>(R.id.infoText).text =
                "${model.playersSymbols[model.currentPlayer]} is playing"
        }

        if (isBotModeOn && !model.gameEnd && model.currentPlayer == Player.SECOND) {
            val key = bot.markField()

            if (key != null) {
                putSymbol(key, model.playersSymbols[Player.SECOND]!!)
            }

            hasAnyoneWon = model.check()

            if (hasAnyoneWon) {
                mainActivity.findViewById<TextView>(R.id.infoText).text =
                    "${model.playersSymbols[model.winner]} has won!"
            } else if (!model.gameEnd) {
                mainActivity.findViewById<TextView>(R.id.infoText).text =
                    "${model.playersSymbols[model.currentPlayer]} is playing"
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun putSymbol(id: Int, symbol: Symbol) {
        when (symbol) {
            Symbol.X -> mainActivity.findViewById<Button>(id).text = "X"
            Symbol.O -> mainActivity.findViewById<Button>(id).text = "O"
            Symbol.EMPTY -> mainActivity.findViewById<TextView>(R.id.infoText).text = "Invalid move!"
        }
    }

    fun attachBot() {
        resetFields()
        isBotModeOn = true
    }

    @SuppressLint("SetTextI18n")
    fun resetFields() {
        model.reset()
        isBotModeOn = false

        for ((k, _) in buttons.buttonIDs) {
            mainActivity.findViewById<Button>(k).text = ""
        }

        mainActivity.findViewById<TextView>(R.id.infoText).text = "Let's play"
    }
}