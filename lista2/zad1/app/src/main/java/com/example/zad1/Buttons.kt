package com.example.zad1

import com.example.zad1.R

class Buttons {
    var buttonIDs: MutableMap<Int, Pair<Int, Int>> = mutableMapOf()

    init {
        // First row
        buttonIDs[R.id.field_0_0] = Pair(0,0)
        buttonIDs[R.id.field_0_1] = Pair(0,1)
        buttonIDs[R.id.field_0_2] = Pair(0,2)
        buttonIDs[R.id.field_0_3] = Pair(0,3)
        buttonIDs[R.id.field_0_4] = Pair(0,4)

        // Second row
        buttonIDs[R.id.field_1_0] = Pair(1,0)
        buttonIDs[R.id.field_1_1] = Pair(1,1)
        buttonIDs[R.id.field_1_2] = Pair(1,2)
        buttonIDs[R.id.field_1_3] = Pair(1,3)
        buttonIDs[R.id.field_1_4] = Pair(1,4)

        // Third row
        buttonIDs[R.id.field_2_0] = Pair(2,0)
        buttonIDs[R.id.field_2_1] = Pair(2,1)
        buttonIDs[R.id.field_2_2] = Pair(2,2)
        buttonIDs[R.id.field_2_3] = Pair(2,3)
        buttonIDs[R.id.field_2_4] = Pair(2,4)

        // Fourth row
        buttonIDs[R.id.field_3_0] = Pair(3,0)
        buttonIDs[R.id.field_3_1] = Pair(3,1)
        buttonIDs[R.id.field_3_2] = Pair(3,2)
        buttonIDs[R.id.field_3_3] = Pair(3,3)
        buttonIDs[R.id.field_3_4] = Pair(3,4)

        // Fifth row
        buttonIDs[R.id.field_4_0] = Pair(4,0)
        buttonIDs[R.id.field_4_1] = Pair(4,1)
        buttonIDs[R.id.field_4_2] = Pair(4,2)
        buttonIDs[R.id.field_4_3] = Pair(4,3)
        buttonIDs[R.id.field_4_4] = Pair(4,4)
    }
}