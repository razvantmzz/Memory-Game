package com.razvantmz.memofy.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.razvantmz.memofy.helpers.CardGenerator
import com.razvantmz.memofy.models.GameCard

class GameViewModel : ViewModel() {

    val NUMBER_OF_COLUMNS: Int = 4

    ///region private members

    private val itemList:MutableLiveData<List<GameCard>> = MutableLiveData()

    ///endregion


    ///region getters

    fun getItemList(): LiveData<List<GameCard>>
    {
        return  itemList
    }

    ///endregion

    ///region public methods

    fun startGameCommand()
    {
        itemList.value = CardGenerator().generateNumberPairs(NUMBER_OF_COLUMNS * 2)
    }

    ///endregion
}