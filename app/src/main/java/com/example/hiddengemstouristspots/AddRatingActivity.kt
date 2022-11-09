package com.example.hiddengemstouristspots

import android.os.Bundle
import android.text.InputFilter
import android.text.Spanned
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hiddengemstouristspots.data.addExperience
import com.example.hiddengemstouristspots.data.get_currentSpots
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
        val editText = findViewById<EditText>(R.id.rating_score)

        // Assigning filters
        editText.filters = arrayOf<InputFilter>(MinMaxFilter(0, 5))
//
//        fun isValidRating(): Boolean{
//            val stringInTextField = binding.ratingScore.text.toString()
//            val rating = stringInTextField.toInt()
//            if(rating !in 0..5){
//                //print
//                val text = "Please insert a rating between 0 and 5!"
//                val duration = Toast.LENGTH_LONG
//                val toast = Toast.makeText(applicationContext, text, duration)
//                toast.show()
//                return false
//            }
//            else{
//                return true
//            }
//        }


        fun get_rating(): Int{


            val stringInTextField = binding.ratingScore.text.toString()
            val rating = stringInTextField.toInt()
            return rating

        }
        fun get_city(): String{
            return binding.cityVal.text.toString()
        }
        fun get_place(): String{
            return binding.spotVal.text.toString()
        }
        fun get_review(): String {
            return binding.reviewVal.text.toString()
        }
        binding.submitRating.setOnClickListener{
            var rating = get_rating()
            var city = get_city()
            var experienceName = get_place()
            var currSpots =  get_currentSpots()
            var review = get_review()
            var shortened = review.toString()
            if(review.length >= 15){
                shortened = review.substring(0, 14) + "..."
            }
            var list = listOf(shortened, review)
            addExperience(1, experienceName, list, rating.toString(), city)

        }

    }



    //override fun onItemClick()

    // Custom class to define min and max for the edit text
    inner class MinMaxFilter() : InputFilter {
        private var intMin: Int = 0
        private var intMax: Int = 0

        // Initialized
        constructor(minValue: Int, maxValue: Int) : this() {
            this.intMin = minValue
            this.intMax = maxValue
        }

        override fun filter(source: CharSequence, start: Int, end: Int, dest: Spanned, dStart: Int, dEnd: Int): CharSequence? {
            try {
                val input = Integer.parseInt(dest.toString() + source.toString())
                if (isInRange(intMin, intMax, input)) {
                    return null
                }
            } catch (e: NumberFormatException) {
                e.printStackTrace()
            }
            return ""
        }

        // Check if input c is in between min a and max b and
        // returns corresponding boolean
        private fun isInRange(a: Int, b: Int, c: Int): Boolean {
            return if (b > a) c in a..b else c in b..a
        }
    }

}