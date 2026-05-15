package com.example.raithabharosahub.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.raithabharosahub.data.model.CropHistory
import kotlinx.coroutines.flow.Flow

@Dao
interface CropDao {
    @Query("SELECT * FROM crop_history ORDER BY id DESC")
    fun getAllCropHistory(): Flow<List<CropHistory>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCropHistory(crop: CropHistory)
}
