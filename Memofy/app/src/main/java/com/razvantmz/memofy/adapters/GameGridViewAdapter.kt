package com.razvantmz.memofy.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.recyclerview.widget.RecyclerView
import com.razvantmz.memofy.databinding.CellGameBinding
import com.razvantmz.memofy.models.GameCard

class GameGridViewAdapter() : BaseAdapter() {

    private var items:List<GameCard> = emptyList()

    class GameCellViewHolder(var binding: CellGameBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: GameCard)
        {
            binding.vFace.setBackgroundColor(item.color)
            binding.vBack.visibility = getBackVisibility(item.isFacedown)
            binding.vFace.visibility = getFaceVisibility(item.isFacedown)
        }

        private fun getFaceVisibility(isFacedown:Boolean):Int
        {
            if(isFacedown)
                return View.INVISIBLE
            return  View.VISIBLE
        }

        private fun getBackVisibility(isFacedown:Boolean): Int
        {
            if(isFacedown)
                return View.VISIBLE
            return  View.INVISIBLE
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        if (convertView == null)
        {
            val viewHolder = GameGridViewAdapter.GameCellViewHolder(
                CellGameBinding.inflate(LayoutInflater.from(parent?.context))
            )
            viewHolder.bind(getItem(position))

            return viewHolder.itemView;
        }

        return convertView
    }

    override fun getItem(p0: Int): GameCard {
        return items[p0]
    }

    override fun getItemId(p0: Int): Long {
        return items[p0].hashCode().toLong()
    }

    override fun getCount(): Int {
        return items.count()
    }

    fun setItemList(items:List<GameCard>)
    {
        this.items = items
        notifyDataSetChanged()
    }
}