package com.example.autochekapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.autochekapplication.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

/**
 * To make android classes use Hilt, we have to annotate it with @AndroidEntryPoint
 */

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var bottomNav : BottomNavigationView
    private lateinit var navHostFragment : FragmentContainerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        bottomNav = findViewById(R.id.bottomNavId)
        navHostFragment = findViewById(R.id.fragmentContainerView)

        //set up bottom app bar
        binding.bottomNavId.background = null
        binding.bottomNavId.menu.getItem(2).isEnabled = false

        //set up bottom nav with fragments
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        val bottomNav = bottomNav.setupWithNavController(navController)
    }
}