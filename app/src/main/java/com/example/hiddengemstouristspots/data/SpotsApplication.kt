package com.example.hiddengemstouristspots.data

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class SpotsApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { SpotRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { SpotRepository(database.spotDao()) }
}