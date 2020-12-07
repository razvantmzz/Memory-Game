package com.razvantmz.memofy.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.recyclerview.widget.RecyclerView
import com.razvantmz.memofy.databinding.CellGameBinding
import com.razvantmz.memofy.models.GameCard

class GameGridViewAdapter(private var interaction: Interaction) : BaseAdapter() {

    interface Interaction
    {
        fun onCardSelected(card:GameCard)
    }

    private var items:List<GameCard> = emptyList()

    class GameCellViewHolder(var binding: CellGameBinding, val interaction: Interaction) : RecyclerView.ViewHolder(binding.root), GameCard.Interaction
    {

        fun bind(item: GameCard)
        {
            binding.vFace.setBackgroundColor(item.color)
            setFacesVisibility(item.isFacedown)
            binding.cvCellContainer.setOnClickListener(View.OnClickListener {
               interaction.onCardSelected(item)
            })
            item.interaction = this
        }

        private fun setFacesVisibility(isFacedown: Boolean)
        {
            binding.vBack.visibility = getBackVisibility(isFacedown)
            binding.vFace.visibility = getFaceVisibility(isFacedown)
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

        override fun onHide() {
            binding.cvCellContainer.visibility = View.INVISIBLE
        }

        override fun onOrientationChanged(isFacedown: Boolean) {
            setFacesVisibility(isFacedown)
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val viewHolder = GameGridViewAdapter.GameCellViewHolder(
            CellGameBinding.inflate(LayoutInflater.from(parent?.context)),
            interaction
        )
        viewHolder.bind(getItem(position))
        return viewHolder.itemView;
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