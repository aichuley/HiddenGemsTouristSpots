package com.example.hiddengemstouristspots

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.View
import android.widget.Button
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

    private val adapter = SpotCardAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerticalListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.verticalRecyclerView.adapter = adapter

        supportActionBar?.title = "Hidden Gems"

        spotViewModel.allSpots.observe(this, Observer { spots ->
            spots?.let { adapter.submitList(it) }
            data = adapter.currentList
        })


        // Specify fixed size to improve performance
        binding.verticalRecyclerView.setHasFixedSize(true)

        // Enable up button for backward navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.fab.setOnClickListener {
            rotator()
            val intent = Intent(this@VerticalListActivity, AddRatingActivity::class.java)
            startActivityForResult(intent, newSpotActivityRequestCode)
        }
        binding.filterSwitch.setOnClickListener{
            if(binding.filterSwitch.isChecked) {
                Log.d("switch", "switch is enabled")
                val appSettingPreferences: SharedPreferences = getSharedPreferences( "AppSettingPreferences", 0)
                val cityName = appSettingPreferences.getString("city", "Austin")
                Log.d("city", cityName.toString())
                spotViewModel.getFilteredSpots(cityName = cityName!!).observe(this) { spots ->
                    spots?.let { adapter.submitList(it) }
                    data = adapter.currentList
                }
            }
            else {
                Log.d("switch", "switch is disabled")
                spotViewModel.allSpots.observe(this, Observer { spots ->
                    spots?.let { adapter.submitList(it) }
                    data = adapter.currentList
                })
            }
        }
    }

    private fun ObjectAnimator.disableViewDuringAnimation(view: View) {

        addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator?) {
                view.isEnabled = false
            }

            override fun onAnimationEnd(animation: Animator?) {
                view.isEnabled = true
            }
        })
    }

    private fun rotator() {
        val animator = ObjectAnimator.ofFloat(binding.fab, View.ROTATION, -360f, 0f)
        animator.duration = 1000
        animator.disableViewDuringAnimation(binding.fab)
        animator.start()
    }

    override fun onItemClick(position: Int) {
        if(binding.filterSwitch.isChecked) {
            Log.d("switch", "switch is enabled")
            val appSettingPreferences: SharedPreferences = getSharedPreferences( "AppSettingPreferences", 0)
            val cityName = appSettingPreferences.getString("city", "Austin")
            Log.d("city", cityName.toString())
            spotViewModel.getFilteredSpots(cityName = cityName!!).observe(this) { spots ->
                spots?.let { adapter.submitList(it) }
                data = adapter.currentList
            }
        }
        else {
            Log.d("switch", "switch is disabled")
            spotViewModel.allSpots.observe(this, Observer { spots ->
                spots?.let { adapter.submitList(it) }
                data = adapter.currentList
            })
        }
//        getList()
        itemIntent = Intent(this, CardDetailedActivity::class.java)

        if (data.get(position).imageResourceId > 0) {
            itemIntent.putExtra("IMAGE_URI", "")
            itemIntent.putExtra("IMAGE_ID", data.get(position).imageResourceId)
        } else {
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

        print("returned from activity")

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
                rating += "/5"
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

    // new method called get list that checks if filtering is turned on
    // if its not, it returns allSpots, other wise it get filteredSpots
    // changes lines 44 and 85
//    fun getList() {
//
//        val appSettingPreferences: SharedPreferences =
//            getSharedPreferences("AppSettingPreferences", 0)
//
//        if(filteringOn) {
//            Log.d("filtering", "filtering is on")
//            val cityName = appSettingPreferences.getString("city", "Austin")
//            spotViewModel.getFilteredSpots(cityName = cityName!!).observe(this) { spots ->
//                spots?.let { adapter.submitList(it) }
//                data = adapter.currentList
//            }
//
//        }
//        else {
//            Log.d("filtering", "filtering is off")
//            spotViewModel.allSpots.observe(this, Observer { spots ->
//                spots?.let { adapter.submitList(it) }
//                data = adapter.currentList
//            })
//        }
//
//
//    }
}