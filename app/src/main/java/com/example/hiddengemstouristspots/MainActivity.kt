package com.example.hiddengemstouristspots

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hiddengemstouristspots.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {
    //main
    private lateinit var binding: ActivityMainBinding
    private lateinit var listIntent: Intent
    private lateinit var reviewItent: Intent
    private lateinit var settingsIntent: Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Setup view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewHiddenGemsButton.setOnClickListener { launchVertical() }

        binding.addHiddenGemButton.setOnClickListener { launchHelper() }
        binding.settingsButton.setOnClickListener { launchSettings() }


    }

    private fun launchVertical() {
        listIntent = Intent(this, VerticalListActivity::class.java)
        startActivity(listIntent)
    }

    private fun launchHelper() {
        reviewItent = Intent(this, AddRatingActivity::class.java)
        startActivity(reviewItent)
    }

    private fun launchSettings() {
        settingsIntent = Intent(this, SettingsActivity::class.java)
        startActivity(settingsIntent)
    }


}