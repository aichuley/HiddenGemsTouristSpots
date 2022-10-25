package com.example.hiddengemstouristspots.data

import com.example.hiddengemstouristspots.model.TouristSpot
import com.example.hiddengemstouristspots.R

/**
 * An object to generate a static list of dogs
 */
object DataSource {
    //changes the images to be video game images
    //changed age text to be type of game
    //change hobby to be the mains in the game
    val spot: List<TouristSpot> = listOf(
        TouristSpot(
            R.drawable.maincampus_hero,
            "UT Austin",
            "College of Texas",
            "5/5 stars"
        )
    )
}
