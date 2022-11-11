package com.example.hiddengemstouristspots

import AppViewModel
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText


class SettingsActivity: AppCompatActivity() {

    private val viewModel: AppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        var submit = findViewById<Button>(R.id.submitNewCity)
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
         fun scaler() {
            val scalex = PropertyValuesHolder.ofFloat(View.SCALE_X, 2f)
            val scaley = PropertyValuesHolder.ofFloat(View.SCALE_Y, 2f)
            val animator = ObjectAnimator.ofPropertyValuesHolder(submit, scalex, scaley)
            animator.repeatCount = 1
            animator.repeatMode = ObjectAnimator.REVERSE
            animator.disableViewDuringAnimation(submit)
            animator.start()
        }

        //on submit do animation and set the new city value
        submit.setOnClickListener {
            scaler()
            var newCity = get_city()
            viewModel.setCity(newCity)
        }
    }

    fun get_city(): String{
        val city = findViewById<TextInputEditText>(R.id.settingsCity)
        return city.toString()
    }


}