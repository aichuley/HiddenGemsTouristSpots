package com.example.hiddengemstouristspots

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Intent
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.InputFilter
import android.text.Spanned
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.hiddengemstouristspots.databinding.AddRatingBinding


class AddRatingActivity : AppCompatActivity() {

    private lateinit var binding: AddRatingBinding
    private lateinit var newImage: Uri

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result ->
        if(result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            binding.previewImage.setImageURI(data?.data)
            newImage = data?.data!!

            val contentResolver = applicationContext.contentResolver

            val takeFlags: Int = Intent.FLAG_GRANT_READ_URI_PERMISSION or
                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION
// Check for the freshest data.
            contentResolver.takePersistableUriPermission(newImage, takeFlags)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_rating)
        binding = AddRatingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val editText = findViewById<EditText>(R.id.rating_score)

        // Assigning filters
        editText.filters = arrayOf<InputFilter>(MinMaxFilter(0, 5))

        binding.selectImageButton.setOnClickListener{
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            resultLauncher.launch(intent)
        }


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
            val replyIntent = Intent()
            if(binding.cityVal.text.toString().isEmpty() ||  binding.spotVal.text.toString().isEmpty() || binding.reviewVal.text.toString().isEmpty()){
                val toast = Toast.makeText(applicationContext, "Please Fill Out All Fields", Toast.LENGTH_LONG)
                toast.show()
            }
            else{
                rotater()
                val rating = get_rating()
                val city = get_city()
                val experienceName = get_place()
                val review = get_review()

                val toast = Toast.makeText(applicationContext, "Thank you for submitting", Toast.LENGTH_LONG)
                toast.show()


                var shortened = review.toString()
                if(review.length >= 15){
                    shortened = review.substring(0, 14) + "..."
                }


                replyIntent.putExtra("IMAGE_ID", 0)
                replyIntent.putExtra("IMAGE_URI", newImage.toString())
                replyIntent.putExtra("NAME", experienceName)
                replyIntent.putExtra("SHORT_REVIEW", shortened)
                replyIntent.putExtra("RATING", rating.toString())
                replyIntent.putExtra("LONG_REVIEW", review)
                replyIntent.putExtra("CITY", city)
                setResult(Activity.RESULT_OK, replyIntent)
                finish()

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