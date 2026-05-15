package com.example.raithabharosahub.data.repository

import com.example.raithabharosahub.data.local.CropDao
import com.example.raithabharosahub.data.model.CropHistory
import kotlinx.coroutines.flow.Flow

class CropRepository(private val cropDao: CropDao) {
    val allCropHistory: Flow<List<CropHistory>> = cropDao.getAllCropHistory()

    suspend fun addCropHistory(crop: CropHistory) {
        cropDao.insertCropHistory(crop)
    }
}
