package com.razvantmz.memofy.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.razvantmz.memofy.helpers.CardGenerator
import com.razvantmz.memofy.models.GameCard
import kotlinx.coroutines.*

class GameViewModel : ViewModel() {

    val NUMBER_OF_COLUMNS: Int = 4

    ///region private members

    private var gameCoroutineScope:CoroutineScope = CoroutineScope(Job() + Dispatchers.IO)
    private var pairCount:Int = 0
    private var foundPairsCount = 0;

    private val itemList:MutableLiveData<List<GameCard>> = MutableLiveData()
    private val score: MutableLiveData<Int> = MutableLiveData()
    private val gameCompleted: MutableLiveData<Boolean> = MutableLiveData()

    var card1: GameCard? = null
    var card2: GameCard? = null

    ///endregion

    ///region getters

    fun getItemList(): LiveData<List<GameCard>>
    {
        return  itemList
    }

    fun getScore(): LiveData<Int>
    {
        return  score
    }

    fun gameEnded(): LiveData<Boolean>
    {
        return  gameCompleted
    }

    ///endregion

    ///region public methods

    fun startGameCommand()
    {
        score.value = 0
        pairCount = NUMBER_OF_COLUMNS * NUMBER_OF_COLUMNS / 2
        foundPairsCount = 0
        gameCompleted.value = false
        itemList.value = CardGenerator().generateNumberPairs(pairCount)
    }

    fun onCardSelected(card: GameCard)
    {
        if(card1 != null && card2 != null)
        {
            return
        }

        gameCoroutineScope.launch {
            testCard(card)
        }
    }

    ///endregion

    private suspend fun testCard(card: GameCard)
    {
        withContext(Dispatchers.Main) {
            card.isFacedown = !card.isFacedown
            card.interaction?.onOrientationChanged(card.isFacedown)
        }
        if(card1 == null)
        {
            card1 = card
            return
        }
        card2 = card
        if(card1?.id == card2?.id)
        {
            resetPairs()
            return
        }
        delay(500)
        if(card1?.pairId == card.pairId)
        {
            withContext(Dispatchers.Main) {
                score.value = score.value!! + 2
                card.interaction?.onHide()
                card1?.interaction?.onHide()
                foundPairsCount++
                onPairFound()
                resetPairs()
            }
            return
        }
        else
        {
            if (score.value!! > 0 )
                score.postValue(score.value!! -1)
        }

        resetPairs()
    }

    private suspend fun resetPairs()
    {
        withContext(Dispatchers.Main) {
            card2?.isFacedown = true
            card2?.interaction?.onOrientationChanged(true)

            card1?.isFacedown = true
            card1?.interaction?.onOrientationChanged(true)

            card1 = null
            card2 = null
        }
    }

    private fun onPairFound()
    {
        if (pairCount == foundPairsCount)
        {
         gameCompleted.value = true
        }
    }
}