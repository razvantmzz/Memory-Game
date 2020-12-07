package com.razvantmz.memofy.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.razvantmz.memofy.adapters.GameGridViewAdapter
import com.razvantmz.memofy.databinding.ActivityGameBinding
import com.razvantmz.memofy.models.GameCard
import com.razvantmz.memofy.viewModels.GameViewModel

class GameActivity : AppCompatActivity() {

    private lateinit var bindings: ActivityGameBinding
    private lateinit var viewModel: GameViewModel
    private lateinit var gameAdapter: GameGridViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        bindings = ActivityGameBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        super.onCreate(savedInstanceState)
        setContentView(bindings.root)

        bindings.gvGame.numColumns = viewModel.NUMBER_OF_COLUMNS
        gameAdapter = GameGridViewAdapter()
        bindings.gvGame.adapter = gameAdapter

        viewModel.getItemList().observe(this, Observer {items ->
            gameAdapter.setItemList(items)
        })

        bindings.btnStart.setOnClickListener(View.OnClickListener {
            viewModel.startGameCommand()
        })
    }
}
