package com.example.raithabharosahub.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.raithabharosahub.data.model.FarmerProfile
import kotlinx.coroutines.flow.Flow

@Dao
interface FarmerDao {
    @Query("SELECT * FROM farmer_profile WHERE id = 1")
    fun getFarmerProfile(): Flow<FarmerProfile?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFarmerProfile(profile: FarmerProfile)
}
