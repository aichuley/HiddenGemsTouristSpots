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
                "5/5 stars",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
                , "Austin"
            )
            spotDao.insert(spot)
            spot = Spot(
                //image from: https://www.istockphoto.com/photos/zilker-park
                R.drawable.zilker_park,
                "",
                "Zilker Metropolitan Park",
                "Sprawling green...",
                "4.8/5 stars",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
                , "Austin"
            )
            spotDao.insert(spot)
            spot = Spot(
                //image from: https://www.gettyimages.ca/photos/texas-state-capitol-building
                R.drawable.capitol,
                "",
                "Texas Capitol",
                "Home to the state...",
                "4.7/5 stars",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
                , "Austin"
            )
            spotDao.insert(spot)
            spot = Spot(
                //image from: https://elanatsui.art/collections/blanton-museum-of-art
                R.drawable.blanton,
                "",
                "Blanton Museum of Art",
                "Art collection of...",
                "4.6/5 stars",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
                , "Austin"
            )
            spotDao.insert(spot)
        }
    }
}