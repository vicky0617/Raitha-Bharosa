package com.example.raithabharosahub.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "farmer_profile")
data class FarmerProfile(
    @PrimaryKey val id: Int = 1,
    val fullName: String,
    val mobileNumber: String,
    val village: String,
    val district: String,
    val primaryCrop: String,
    val selectedLanguage: String = "en",
    val profileImageUri: String? = null
)
