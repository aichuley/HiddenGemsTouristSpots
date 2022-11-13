package com.example.hiddengemstouristspots

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.hiddengemstouristspots.adapter.SpotCardAdapter
import com.example.hiddengemstouristspots.data.Spot
import com.example.hiddengemstouristspots.data.SpotViewModel
import com.example.hiddengemstouristspots.data.SpotViewModelFactory
import com.example.hiddengemstouristspots.data.SpotsApplication
import com.example.hiddengemstouristspots.databinding.ActivityVerticalListBinding

class VerticalListActivity : AppCompatActivity(), RecyclerViewInterface {

    private lateinit var binding: ActivityVerticalListBinding
    private lateinit var itemIntent: Intent
    private lateinit var data: List<Spot>

    private val newSpotActivityRequestCode = 1
    private val spotViewModel: SpotViewModel by viewModels {
        SpotViewModelFactory((application as SpotsApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerticalListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = SpotCardAdapter(this)
        binding.verticalRecyclerView.adapter = adapter

        spotViewModel.allSpots.observe(this, Observer { spots ->
            // Update the cached copy of the words in the adapter.
            spots?.let { adapter.submitList(it) }
            data = adapter.currentList
        })


        // Specify fixed size to improve performance
        binding.verticalRecyclerView.setHasFixedSize(true)

        // Enable up button for backward navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.fab.setOnClickListener{
            val intent = Intent(this@VerticalListActivity, AddRatingActivity::class.java)
            startActivityForResult(intent, newSpotActivityRequestCode)
        }
    }

    override fun onItemClick(position: Int) {
        itemIntent = Intent(this, CardDetailedActivity::class.java)

        if(data.get(position).imageResourceId > 0){
            itemIntent.putExtra("IMAGE_URI", "")
            itemIntent.putExtra("IMAGE_ID", data.get(position).imageResourceId)
        }
        else{
            itemIntent.putExtra("IMAGE_ID", 0)
            itemIntent.putExtra("IMAGE_URI", data.get(position).imageUri)
        }
        itemIntent.putExtra("NAME", data.get(position).name)
        itemIntent.putExtra("SHORT_REVIEW", data.get(position).short_summary)
        itemIntent.putExtra("LONG_REVIEW", data.get(position).long_summary)
        itemIntent.putExtra("RATING", data.get(position).rating)
        itemIntent.putExtra("CITY", data.get(position).city)

        startActivity(itemIntent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newSpotActivityRequestCode && resultCode == Activity.RESULT_OK) {
            var imageResourceId = 0
            var name = ""
            var short_summary = ""
            var rating = ""
            var long_summary = ""
            var city = ""
            var image = ""
            intentData?.getIntExtra("IMAGE_ID", 0)?.let { reply ->
                imageResourceId = reply
            }
            intentData?.getStringExtra("IMAGE_URI")?.let { reply ->
                image = reply
            }
            intentData?.getStringExtra("NAME")?.let { reply ->
                name = reply
            }
            intentData?.getStringExtra("SHORT_REVIEW")?.let { reply ->
                short_summary = reply
            }
            intentData?.getStringExtra("RATING")?.let { reply ->
                rating = reply
            }
            intentData?.getStringExtra("LONG_REVIEW")?.let { reply ->
                long_summary = reply
            }
            intentData?.getStringExtra("CITY")?.let { reply ->
                city = reply
            }


            val spot = Spot(imageResourceId, image, name, short_summary, rating, long_summary, city)
            spotViewModel.insert(spot)
        } else {
            Toast.makeText(
                applicationContext,
                "empty not saved",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}