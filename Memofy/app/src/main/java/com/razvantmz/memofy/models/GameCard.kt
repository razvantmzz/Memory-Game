package com.razvantmz.memofy.models

class GameCard(
    var id:Int,
    var pairId:Int,
    var color: Int,
    var isFacedown: Boolean = true,
    var interaction: Interaction? = null
)
{
    interface Interaction
    {
        fun onHide()
        fun onOrientationChanged(isFacedown: Boolean)
    }
}