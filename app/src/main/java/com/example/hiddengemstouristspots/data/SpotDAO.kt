package com.example.hiddengemstouristspots.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface SpotDAO {

    // The flow always holds/caches latest version of data. Notifies its observers when the
    // data has changed.
    @Query("SELECT * FROM spot_table")
    fun getSpots(): Flow<List<Spot>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(spot: Spot)

    @Query("SELECT * FROM spot_table WHERE city = :cityName")
    fun getFilteredSpots(cityName: String): Flow<List<Spot>>

}