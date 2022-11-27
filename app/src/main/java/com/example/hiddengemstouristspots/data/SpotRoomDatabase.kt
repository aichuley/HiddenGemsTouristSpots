package com.example.hiddengemstouristspots.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.hiddengemstouristspots.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Spot::class), version = 1, exportSchema = false)
public abstract class SpotRoomDatabase : RoomDatabase() {

    abstract fun spotDao(): SpotDAO


    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: SpotRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): SpotRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SpotRoomDatabase::class.java,
                    "spot_database"
                ).addCallback(SpotDatabaseCallback(scope)).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

    private class SpotDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.spotDao())
                }
            }
        }

        suspend fun populateDatabase(spotDao: SpotDAO) {
            var spot = Spot(
                //image from: https://www.utexas.edu/about/overview
                R.drawable.maincampus_hero,
                "",
                "UT Austin",
                "College of Texas",
                "5/5",
                "The University of Texas at Austin is a public research university in Austin, Texas. It was founded in 1883 and is the oldest institution in the University of Texas System."
                , "Austin"
            )
            spotDao.insert(spot)
            spot = Spot(
                //image from: https://www.istockphoto.com/photos/zilker-park
                R.drawable.zilker_park,
                "",
                "Zilker Metropolitan Park",
                "Sprawling green...",
                "4/5",
                "Zilker Metropolitan Park is a recreational area in south Austin, Texas at the juncture of Barton Creek and the Colorado River that comprises over 350 acres of publicly owned land. It is named after its benefactor, Andrew Jackson Zilker, who donated the land to the city in 1917."
                , "Austin"
            )
            spotDao.insert(spot)
            spot = Spot(
                //image from: https://www.gettyimages.ca/photos/texas-state-capitol-building
                R.drawable.capitol,
                "",
                "Texas Capitol",
                "Home to the state...",
                "4.5/5",
                "The Texas State Capitol is the capitol and seat of government of the American state of Texas. Located in downtown Austin, Texas, the structure houses the offices and chambers of the Texas Legislature and of the Governor of Texas."
                , "Austin"
            )
            spotDao.insert(spot)
            spot = Spot(
                //image from: https://elanatsui.art/collections/blanton-museum-of-art
                R.drawable.blanton,
                "",
                "Blanton Museum of Art",
                "Art collection of...",
                "4.5/5",
                "The Jack S. Blanton Museum of Art at the University of Texas at Austin is one of the largest university art museums in the U.S. with 189,340 square feet devoted to temporary exhibitions, permanent collection galleries, storage, administrative offices, classrooms, a print study room, an auditorium, shop, and cafe.",
                "Austin"
            )
            spotDao.insert(spot)
            spot = Spot(
                //image from: https://www.coolworks.com/grand-canyon-south-rim/profile
                R.drawable.grandcanyon,
                "",
                "Grand Canyon",
                "The Grand Canyon is a steep-sided canyon...",
                "4.5/5",
                "Grand Canyon National Park, in Arizona, is home to much of the immense Grand Canyon, with its layered bands of red rock revealing millions of years of geological history."
                , "Arizona"
            )
            spotDao.insert(spot)
            spot = Spot(
                //image from: https://www.planetware.com/tourist-attractions-/wyoming-yellowstone-national-park-us-wy-yellow.htm
                R.drawable.usa_wyoming_yellowstone_grand_prismatic_spring,
                "",
                "Yellow Stone National Park",
                "Yellowstone National Park is a nearly 3,500-sq.-mile...",
                "4.5/5",
                "Mostly in Wyoming, the park spreads into parts of Montana and Idaho too. Yellowstone features dramatic canyons, alpine rivers, lush forests, hot springs and gushing geysers, including its most famous, Old Faithful."
                , "Wyoming"
            )
            spotDao.insert(spot)
            spot = Spot(
                //image from: https://www.tripsavvy.com/thmb/X1noqpPb0L654YUCcthg9E1Fdy4=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/GettyImages-1164998882-6bcd8aa5b6124528b16b56089f4bf47d.jpg
                R.drawable.sanantonio,
                "",
                "San Antonio River Walk",
                "The San Antonio River Walk is a city park...",
                "4.5/5",
                "The River Walk winds and loops under bridges as two parallel sidewalks lined with restaurants and shops, connecting the major tourist draws such as the Shops at Rivercenter."
                , "San Antonio"
            )
            spotDao.insert(spot)
            spot = Spot(
                //image from: https://www.history.com/topics/landmarks/golden-gate-bridge
                R.drawable.topic_golden_gate_bridge_gettyimages_177770941,
                "",
                "Golden Gate Bridge",
                "The Golden Gate Bridge is a suspension bridge...",
                "4.8/5",
                "The structure links the U.S. city of San Francisco, California—the northern tip of the San Francisco Peninsula—to Marin County, carrying both U.S. Route 101 and California State Route 1 across the strait."
                , "San Francisco"
            )
            spotDao.insert(spot)
            spot = Spot(
                //image from: https://www.expressnews.com/news/local/article/Gorillas-returning-to-San-Antonio-Zoo-after-16846222.php
                R.drawable.zoo,
                "",
                "San Antonio Zoo",
                "Recently named the Best Zoo in Texas...",
                "4.3/5",
                "The San Antonio Zoo is an Association of Zoos and Aquariums-accredited zoo in Midtown San Antonio, Texas, United States. It is located in the city's Brackenridge Park."
                , "San Antonio"
            )
            spotDao.insert(spot)
        }
    }
}