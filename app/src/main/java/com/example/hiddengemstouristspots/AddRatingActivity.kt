package com.example.hiddengemstouristspots

import AppViewModel
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.text.InputFilter
import android.text.Spanned
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.hiddengemstouristspots.databinding.AddRatingBinding


class AddRatingActivity : AppCompatActivity() {

    private val viewModel: AppViewModel by viewModels()
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

         fun ObjectAnimator.disableViewDuringAnimation(view: View) {

            addListener(object: AnimatorListenerAdapter(){
                override fun onAnimationStart(animation: Animator?) {
                    view.isEnabled = false
                }

                override fun onAnimationEnd(animation: Animator?) {
                    view.isEnabled = true
                }
            })
        }

        fun rotater() {
            val animator = ObjectAnimator.ofFloat(binding.submitRating, View.ROTATION,-360f,0f)
            animator.duration = 1000
            animator.disableViewDuringAnimation(binding.submitRating)
            animator.start()
        }

        binding.submitRating.setOnClickListener{

            if(binding.cityVal.text.toString().isEmpty() ||  binding.spotVal.text.toString().isEmpty() || binding.reviewVal.text.toString().isEmpty()){
                val toast = Toast.makeText(applicationContext, "Please Fill Out All Fields", Toast.LENGTH_LONG)
                toast.show()
            }
            else{
                rotater()
                var rating = get_rating()
                var city = get_city()
                var experienceName = get_place()
                var review = get_review()

                val toast = Toast.makeText(applicationContext, "Thank you for submitting", Toast.LENGTH_LONG)
                toast.show()


                var shortened = review.toString()
                if(review.length >= 15){
                    shortened = review.substring(0, 14) + "..."
                }
                var list = listOf(shortened, review)
                viewModel.addExperience(1, experienceName, list, rating.toString(), city)

            }

        }

    }


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