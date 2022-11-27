package com.example.hiddengemstouristspots

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import com.example.hiddengemstouristspots.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {
    //main
    private lateinit var binding: ActivityMainBinding
    private lateinit var listIntent: Intent
    private lateinit var reviewItent: Intent
    private lateinit var settingsIntent: Intent
    private lateinit var music: Intent


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = "Hidden Gems"

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewHiddenGemsButton.setOnClickListener { launchVertical() }

        binding.settingsButton.setOnClickListener { launchSettings() }
        binding.musicButton.setOnClickListener{
            var mediaplayer = MediaPlayer.create(this, R.raw.tropical)

            mediaplayer.start()


            // time count down for 30 seconds,
            // with 1 second as countDown interval
            object : CountDownTimer(5000, 1000) {

                // Callback function, fired on regular interval
                override fun onTick(millisUntilFinished: Long) {

                }

                // Callback function, fired
                // when the time is up
                override fun onFinish() {
                    mediaplayer.stop()
                    mediaplayer.reset();
                }
            }.start()
        }

    }


    private fun launchVertical() {
        listIntent = Intent(this, VerticalListActivity::class.java)
        startActivity(listIntent)
    }

    private fun launchSettings() {
        settingsIntent = Intent(this, SettingsActivity::class.java)
        startActivity(settingsIntent)
    }
}