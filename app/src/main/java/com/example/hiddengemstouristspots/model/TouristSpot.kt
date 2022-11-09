package com.example.hiddengemstouristspots.model

import androidx.annotation.DrawableRes

/**
 * A data class to represent the information presented in the dog card
 */
data class TouristSpot(
    @DrawableRes val imageResourceId: Int,
    val name: String,
    val short_summary: String,
    val rating: String,
    val long_summary: String,
    val city: String,
)
