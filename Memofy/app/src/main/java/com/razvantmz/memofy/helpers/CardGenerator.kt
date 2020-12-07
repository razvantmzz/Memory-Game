package com.razvantmz.memofy.helpers

import android.graphics.Color
import com.razvantmz.memofy.models.GameCard
import java.util.*
import kotlin.collections.ArrayList

class CardGenerator {
    fun generateNumberPairs(pairCount: Int): ArrayList<GameCard>
    {
        var gameCardList = ArrayList<GameCard>()

        val randomValues = List(pairCount) { getRandomId() }
        for (value in randomValues)
        {
            val color: Int = Color.argb(255, (1..255).random(), (1..255).random(), (1..255).random())
            var card = GameCard(getRandomId(), value, color)
            var pair = GameCard(getRandomId(), value, color)
            gameCardList.add(card)
            gameCardList.add(pair)
        }

        gameCardList.shuffle()
        return gameCardList
    }

    fun getRandomId(): Int
    {
        return (100..1000).random() + (1001..2000).random()
    }
}