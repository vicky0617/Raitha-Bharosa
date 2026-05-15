package com.example.raithabharosahub.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.raithabharosahub.data.local.AppDatabase
import com.example.raithabharosahub.data.model.FarmerProfile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class FarmerViewModel(application: Application) : AndroidViewModel(application) {
    private val farmerDao = AppDatabase.getDatabase(application).farmerDao()
    val farmerProfile: Flow<FarmerProfile?> = farmerDao.getFarmerProfile()

    fun saveProfile(profile: FarmerProfile) {
        viewModelScope.launch {
            farmerDao.insertFarmerProfile(profile)
        }
    }
}
