package com.example.zad1

import kotlin.math.max
import kotlin.math.min

// Bot is always second player
class Bot(private var model: Model, private var buttons: Buttons) {

    fun markField(): Int? {
        for (i in 0..4) {
            for (j in 0..4) {
                if (model.board[i][j].player == Player.FIRST) {
                    val coordinates = checkNeighbours(i, j)

                    if (coordinates != null) {
                        model.markField(coordinates.first, coordinates.second)
                        return buttons.findKey(coordinates)
                    }
                }
            }
        }

        model.changePlayer()
        return null
    }

    private fun checkNeighbours(row: Int, column: Int): Pair<Int, Int>? {
        for (i in max(0, row - 1)..min(4, row + 1)) {
            for (j in max(0, column - 1)..min(4, column + 1)) {
                if (i != row && j != column && model.board[i][j].symbol == Symbol.EMPTY) {
                    return Pair(i, j)
                }
            }
        }

        return null
    }
}