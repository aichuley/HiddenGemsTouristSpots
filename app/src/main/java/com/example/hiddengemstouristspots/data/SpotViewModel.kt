package com.example.hiddengemstouristspots.data

import android.graphics.drawable.Drawable
import androidx.lifecycle.*
import kotlinx.coroutines.launch

class SpotViewModel(private val repository: SpotRepository) : ViewModel() {

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allSpots: LiveData<List<Spot>> = repository.allSpots.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(spot: Spot) = viewModelScope.launch {
        repository.insert(spot)
    }



    fun getFilteredSpots(cityName: String): LiveData<List<Spot>>  {
        return repository.getFilteredSpots(cityName).asLiveData()
    }
}

class SpotViewModelFactory(private val repository: SpotRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SpotViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SpotViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}