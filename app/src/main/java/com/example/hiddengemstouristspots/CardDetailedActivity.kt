package com.example.hiddengemstouristspots

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hiddengemstouristspots.databinding.CardDetailedBinding

class CardDetailedActivity : AppCompatActivity(){

    private lateinit var binding: CardDetailedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.card_detailed)
        binding = CardDetailedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = getIntent().getStringExtra("NAME")
        val long_summary = getIntent().getStringExtra("LONG_REVIEW")
        val rating = getIntent().getStringExtra("RATING")
        val image_id = getIntent().getIntExtra("IMAGE_ID", -1)
        val imageUri = getIntent().getStringExtra("IMAGE_URI")

        binding.name.setText(name)
        binding.longDescription.setText(long_summary)
        binding.rating.setText(rating)
        if (image_id > 0) {
            binding.image.setImageResource(image_id.toInt())
        }
        else
            binding.image.setImageURI(Uri.parse(imageUri))

        binding.getDirections.setOnClickListener{
            val queryUrl: Uri = Uri.parse("https://www.google.com/search?q=${name}")
            val intent = Intent(Intent.ACTION_VIEW, queryUrl)
            startActivity(intent)
        }
    }
}