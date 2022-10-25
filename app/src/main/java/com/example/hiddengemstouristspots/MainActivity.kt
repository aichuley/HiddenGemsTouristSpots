package com.example.hiddengemstouristspots

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat.startActivity
import com.example.hiddengemstouristspots.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {
    //main
    private lateinit var binding: ActivityMainBinding
    private lateinit var listIntent: Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Setup view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fab.setOnClickListener { launchVertical() }
    }

    private fun launchVertical() {
        listIntent = Intent(this, VerticalListActivity::class.java)
        startActivity(listIntent)
    }
}

// Deepika's commit