package com.razvantmz.memofy.helpers

import android.graphics.Color
import com.razvantmz.memofy.models.GameCard
import java.util.*
import kotlin.collections.ArrayList

class CardGenerator {
    fun generateNumberPairs(pairCount: Int): ArrayList<GameCard>
    {
        var gameCardList = ArrayList<GameCard>()

        val randomValues = List(pairCount) { (1..pairCount+1).random() }
        val rnd = Random()
        for (value in randomValues)
        {
            val color: Int = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
            var card = GameCard(value, color)
            gameCardList.add(card)
            gameCardList.add(card)
        }

        gameCardList.shuffle()
        return gameCardList
    }
}