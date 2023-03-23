package com.example.ahorcado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import com.example.ahorcado.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        binding.btnPlay.setOnClickListener { startGame() }
    }

    fun init(){
        val defaultGameMode: RadioButton = binding.radioGroup.getChildAt(0) as RadioButton
        val defaultCategoryMode: RadioButton = binding.radioGroup2.getChildAt(0) as RadioButton
        defaultGameMode.isChecked = true
        defaultCategoryMode.isChecked = true
    }

    fun startGame(){
        val gameMode = findViewById<RadioButton>(binding.radioGroup.checkedRadioButtonId)
        val categoryGame = findViewById<RadioButton>(binding.radioGroup2.checkedRadioButtonId)

        val intent = Intent(this, Game::class.java).apply {
            putExtra("gameMode", gameMode.text.toString())
            putExtra("categoryGame", categoryGame.text.toString())
        }
        startActivity(intent)
    }
}