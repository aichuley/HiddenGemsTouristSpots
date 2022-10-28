package com.example.hiddengemstouristspots

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hiddengemstouristspots.data.DataSource
import com.example.hiddengemstouristspots.model.TouristSpot
import com.example.hiddengemstouristspots.databinding.AddRatingBinding


class AddRatingActivity : AppCompatActivity() {

    private lateinit var binding: AddRatingBinding
 //   private lateinit var listIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_rating)
        binding = AddRatingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    //override fun onItemClick()

}