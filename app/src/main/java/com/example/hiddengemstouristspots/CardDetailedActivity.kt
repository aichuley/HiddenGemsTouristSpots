package com.example.hiddengemstouristspots

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hiddengemstouristspots.adapter.DogCardAdapter
import com.example.hiddengemstouristspots.databinding.ActivityVerticalListBinding
import com.example.hiddengemstouristspots.databinding.CardDetailedBinding

class CardDetailedActivity : AppCompatActivity(){

    private lateinit var binding: CardDetailedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.card_detailed)
        binding = CardDetailedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = getIntent().getStringExtra("NAME")
        val long_summary = getIntent().getStringExtra("LONG_SUMMARY")
        val rating = getIntent().getStringExtra("RATING")
        val image_id = getIntent().getIntExtra("IMAGE_ID", -1)

        binding.name.setText(name)
        binding.longDescription.setText(long_summary)
        binding.rating.setText(rating)
        if (image_id != null) {
            binding.image.setImageResource(image_id.toInt())
        }
    }
}