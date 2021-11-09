package com.example.autochekapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.autochekapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //set up bottom app bar
        binding.bottomNavId.background = null
        binding.bottomNavId.menu.getItem(2).isEnabled = false
    }
}