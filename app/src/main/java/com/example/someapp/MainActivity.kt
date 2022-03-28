package com.example.someapp

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.*
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.fragmentContainerView2)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)

        val sharedPreferences : SharedPreferences = applicationContext.getSharedPreferences("welcome", MODE_PRIVATE)
        if (sharedPreferences.getBoolean("firstTime", false) == false){
            bottomNavigationView.visibility = GONE
        }
        bottomNavigationView.setupWithNavController(navController)

    }

}