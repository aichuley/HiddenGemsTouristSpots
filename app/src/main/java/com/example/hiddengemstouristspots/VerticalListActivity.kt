package com.example.hiddengemstouristspots

import AppViewModel
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.hiddengemstouristspots.adapter.DogCardAdapter
import com.example.hiddengemstouristspots.databinding.ActivityVerticalListBinding
import com.example.hiddengemstouristspots.model.TouristSpot

class VerticalListActivity : AppCompatActivity(), RecyclerViewInterface {

    private lateinit var binding: ActivityVerticalListBinding
    private lateinit var itemIntent: Intent
    private lateinit var data: List<TouristSpot>

    override fun onCreate(savedInstanceState: Bundle?) {
        val viewModel: AppViewModel by viewModels()
        data = viewModel.getCurrentSpots()
        super.onCreate(savedInstanceState)
        binding = ActivityVerticalListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.verticalRecyclerView.adapter = DogCardAdapter(
            applicationContext, this,
            viewModel
        )

        // Specify fixed size to improve performance
        binding.verticalRecyclerView.setHasFixedSize(true)

        // Enable up button for backward navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onItemClick(position: Int) {
        itemIntent = Intent(this, CardDetailedActivity::class.java)

        itemIntent.putExtra("NAME", data.get(position).name)
        itemIntent.putExtra("LONG_SUMMARY", data.get(position).long_summary)
        itemIntent.putExtra("RATING", data.get(position).rating)
        itemIntent.putExtra("IMAGE_ID", data.get(position).imageResourceId)

        startActivity(itemIntent)
    }
}
