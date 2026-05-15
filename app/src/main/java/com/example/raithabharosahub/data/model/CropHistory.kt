package com.example.raithabharosahub.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "crop_history")
data class CropHistory(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val cropName: String,
    val sowingDate: String,
    val harvestDate: String?,
    val yield: String?,
    val season: String
)
