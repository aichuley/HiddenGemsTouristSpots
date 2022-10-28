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
    val spot: MutableList<TouristSpot> = mutableListOf(
        TouristSpot(
            R.drawable.maincampus_hero,
            "UT Austin",
            "College of Texas",
            "5/5 stars",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
        ),
        TouristSpot(
            R.drawable.zilker_park,
            "Zilker Metropolitan Park",
            "Sprawling green space with recreation",
            "4.8/5 stars",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
        ),
        TouristSpot(
            R.drawable.capitol,
            "Texas Capitol",
            "Home to the state legislature",
            "4.7/5 stars",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
        ),
        TouristSpot(
            R.drawable.blanton,
            "Blanton Museum of Art",
            "Art collection of University of Texas",
            "4.6/5 stars",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
        ),
    )
}

fun addExperience(photo: Int, name: String, summaries: List<String>, rating: String){
    //create tourist spot here
    DataSource.spot.add(TouristSpot(photo, name, summaries.get(0), rating, summaries.get(1)))
}