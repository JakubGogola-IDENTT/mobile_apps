package com.example.zad2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlin.math.sqrt
import kotlin.random.Random

class Logic {

    // Points
    var points: Int = 20
    var totalPoints: Int = 0

    // Numbers
    var lowerNum: Int = 0
    var higherNum: Int = 0
    private var secretNum: Int = 0

    // Divisors of secret number
    private var divisors: MutableList<Int> = mutableListOf(1)

    private fun numberDivisors() {
        this.divisors = mutableListOf(1, this.secretNum)
        val sqrt = sqrt(this.secretNum.toDouble()).toInt()

        for(i in 2..sqrt) {
            if (this.secretNum % i == 0) {
                this.divisors.add(i)
            }
        }
    }

    fun nextNumber() {
        this.lowerNum = Random.nextInt(1, 1000)
        this.higherNum = this.lowerNum + 20
        this.secretNum = Random.nextInt(this.lowerNum, this.higherNum)

        this.numberDivisors()
    }


    fun restart() {
        this.totalPoints = 0
        this.points = 20
        this.nextNumber()
    }

    fun check(answer: Int): Boolean {
        return if (answer == this.secretNum) {
            this.totalPoints += this.points
            this.points = 20
            true
        } else {
            if (this.points > 0) {
                this.points--
            }
            false
        }
    }

    fun checkOrder(answer: Int): String {
        return if (answer < this.secretNum) {
            "greater"
        } else {
            "lower"
        }
    }

    fun getHint(): Int {
        var divisor = 1

        if (this.divisors.size > 0) {
            var pos = 0

            if (this.divisors.size > 1) {
                pos = Random.nextInt(0, this.divisors.size - 1)
            }

            divisor = this.divisors[pos]
            this.divisors.removeAt(pos)
        }

        if (this.points >= 5) {
            this.points -= 5
        } else {
            this.points = 0
        }

        return divisor
    }
}