package com.example.hiddengemstouristspots.data

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class SpotRepository(private val spotDao: SpotDAO) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allSpots: Flow<List<Spot>> = spotDao.getSpots()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(spot: Spot) {
        spotDao.insert(spot)
    }

    @WorkerThread
    fun getFilteredSpots(cityName: String): Flow<List<Spot>> {
        return spotDao.getFilteredSpots(cityName)
    }
}