package com.example.raithabharosahub.data.repository

import com.example.raithabharosahub.data.local.FarmerDao
import com.example.raithabharosahub.data.model.FarmerProfile
import kotlinx.coroutines.flow.Flow

class FarmerRepository(private val farmerDao: FarmerDao) {
    val farmerProfile: Flow<FarmerProfile?> = farmerDao.getFarmerProfile()

    suspend fun saveFarmerProfile(profile: FarmerProfile) {
        farmerDao.insertFarmerProfile(profile)
    }
}
