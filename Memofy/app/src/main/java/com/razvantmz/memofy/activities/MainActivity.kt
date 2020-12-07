package com.razvantmz.memofy.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.razvantmz.memofy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var bindings: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        bindings = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(bindings.root)

        bindings.btnMainActivityStart.setOnClickListener(View.OnClickListener {
            var intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
        })
    }
}
