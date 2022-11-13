package com.example.hiddengemstouristspots.data

import android.graphics.Bitmap
import androidx.annotation.DrawableRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "spot_table")
data class Spot(
        @ColumnInfo(name = "imageID") @DrawableRes val imageResourceId: Int,
        @ColumnInfo(name = "imageUri") var imageUri: String,
        @PrimaryKey @ColumnInfo(name = "name") val name: String,
        @ColumnInfo(name = "shortSummary") val short_summary: String,
        @ColumnInfo(name = "rating") val rating: String,
        @ColumnInfo(name = "longSummary") val long_summary: String,
        @ColumnInfo(name = "city") val city: String
)