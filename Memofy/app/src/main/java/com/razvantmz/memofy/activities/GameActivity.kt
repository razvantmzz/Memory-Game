package com.razvantmz.memofy.activities

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.razvantmz.memofy.R
import com.razvantmz.memofy.adapters.GameGridViewAdapter
import com.razvantmz.memofy.databinding.ActivityGameBinding
import com.razvantmz.memofy.models.GameCard
import com.razvantmz.memofy.viewModels.GameViewModel

class GameActivity : AppCompatActivity(), GameGridViewAdapter.Interaction {

    private lateinit var bindings: ActivityGameBinding
    private lateinit var viewModel: GameViewModel
    private lateinit var gameAdapter: GameGridViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        bindings = ActivityGameBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        super.onCreate(savedInstanceState)
        setContentView(bindings.root)

        bindings.gvGame.numColumns = viewModel.NUMBER_OF_COLUMNS
        gameAdapter = GameGridViewAdapter(this)
        bindings.gvGame.adapter = gameAdapter

        viewModel.getItemList().observe(this, Observer {items ->
            gameAdapter.setItemList(items)
        })
        viewModel.getScore().observe(this, Observer {score ->
            bindings.tvScore.text = "$score pct"
        })
        viewModel.gameEnded().observe(this, Observer {
            if(!it)
            {
                bindings.vsGameCompleted.visibility = View.GONE
            }
            else
            {
                if (bindings.vsGameCompleted.parent != null) {
                    val view = bindings.vsGameCompleted.inflate()
                    view.findViewById<TextView>(R.id.tv_score).text = viewModel.getScore().value.toString()
                } else {
                    bindings.vsGameCompleted.visibility = View.VISIBLE;
                    findViewById<TextView>(R.id.tv_score).text = viewModel.getScore().value.toString()
                }
            }
        })
        bindings.btnStart.setOnClickListener(View.OnClickListener {
            viewModel.startGameCommand()
        })
    }

    override fun onCardSelected(card: GameCard) {
        viewModel.onCardSelected(card)
    }
}
