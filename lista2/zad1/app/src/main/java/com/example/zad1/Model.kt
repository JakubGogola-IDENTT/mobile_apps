package com.example.zad1

import kotlin.random.Random

class Model {
    //Winner
    var winner: Player = Player.NONE

    var currentPlayer: Player = Player.FIRST

    var gameEnd: Boolean = false

    //Array with dimensions 4 x 4
    var board = arrayOf<Array<Field>>()

    var playersSymbols: MutableMap<Player, Symbol> = mutableMapOf(Player.FIRST to Symbol.O, Player.SECOND to Symbol.X)

    init {
        for (i in 0..4) {
            var row = arrayOf<Field>()
            for (j in 0..4) {
                row += Field(Player.NONE, Symbol.EMPTY)
            }
            board += row
        }
    }

    fun markField(row: Int, column: Int): Symbol {
        val field = board[row][column]
        if (field.symbol == Symbol.EMPTY && field.player == Player.NONE) {
            field.symbol = playersSymbols[currentPlayer]!!
            field.player = currentPlayer

            currentPlayer = if (currentPlayer == Player.FIRST) {
                Player.SECOND
            } else {
                Player.FIRST
            }

            return field.symbol
        }

        return Symbol.EMPTY
    }

    private fun shuffleSymbols() {
        val r = Random.nextInt(0, 10)
        if (r < 5) {
            playersSymbols[Player.FIRST] = Symbol.O
            playersSymbols[Player.SECOND] = Symbol.X
        } else {
            playersSymbols[Player.FIRST] = Symbol.X
            playersSymbols[Player.SECOND] = Symbol.O
        }
    }

    fun check(): Boolean {
        if (!gameEnd) {
            if (!checkHorizontal()) {
                if (!checkVertical()) {
                    if (!checkCross()) {
                        return false
                    }
                }
            }
        }
        gameEnd = true
        return true
    }

    private fun checkHorizontal(): Boolean {
        var hasAnyoneWon = false

        for (i in 0..4) {
            var player = Player.NONE
            var isRowValid = true
            if (board[i][0].symbol != Symbol.EMPTY) {
                player = board[i][0].player
                val symbol = board[i][0].symbol
                for (f in board[i]) {
                    if (f.symbol != symbol || f.player != player) {
                        isRowValid = false
                        break
                    }
                }
            } else {
                isRowValid = false
            }
            if (isRowValid) {
                winner = player
                hasAnyoneWon = true
                break
            }
        }
        return hasAnyoneWon
    }

    private fun checkVertical(): Boolean {
        var hasAnyoneWon = false

        for (i in 0..4) {
            var player = Player.NONE
            var isColumnValid = true
            if (board[0][i].symbol != Symbol.EMPTY) {
                player = board[0][i].player
                val symbol = board[0][i].symbol
                for (j in 0..4) {
                    if (board[j][i].symbol != symbol || board[j][i].player != player) {
                        isColumnValid = false
                        break
                    }
                }
            } else {
                isColumnValid = false
            }
            if (isColumnValid) {
                winner = player
                hasAnyoneWon = true
                break
            }
        }
        return hasAnyoneWon
    }

    private fun checkCross(): Boolean {
        var player: Player
        var symbol: Symbol
        var isDiagonalValid: Boolean

        if (board[0][0].symbol != Symbol.EMPTY) {
            isDiagonalValid = true
            player = board[0][0].player
            symbol = board[0][0].symbol
            for (i in 0..4) {
                if (board[i][i].symbol != symbol || board[i][i].player != player) {
                    isDiagonalValid = false
                    break
                }
            }
            if (isDiagonalValid) {
                winner = player
                return true
            }
        }

        if (board[0][4].symbol != Symbol.EMPTY) {
            isDiagonalValid = true
            player = board[0][4].player
            symbol = board[0][4].symbol
            var columnIndex = 4
            for (i in 0..4) {
                if (board[i][columnIndex].symbol != symbol || board[i][columnIndex].player != player) {
                    isDiagonalValid = false
                    break
                }
                columnIndex--
            }
            if (isDiagonalValid) {
                winner = player
                return true
            }
        }

        return false
    }


    fun reset() {
        for (i in 0..4) {
            for (j in 0..4) {
                board[i][j].player = Player.NONE
                board[i][j].symbol = Symbol.EMPTY
            }
        }
        shuffleSymbols()
        winner = Player.NONE
        currentPlayer = Player.FIRST
        gameEnd = false
    }
}