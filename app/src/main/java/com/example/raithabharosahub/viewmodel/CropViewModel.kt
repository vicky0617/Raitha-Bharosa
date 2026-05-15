package com.example.raithabharosahub.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.raithabharosahub.data.local.AppDatabase
import com.example.raithabharosahub.data.model.CropHistory
import com.example.raithabharosahub.data.repository.CropRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CropViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: CropRepository
    val allCropHistory: Flow<List<CropHistory>>

    init {
        val cropDao = AppDatabase.getDatabase(application).cropDao()
        repository = CropRepository(cropDao)
        allCropHistory = repository.allCropHistory
    }

    fun addCropHistory(crop: CropHistory) {
        viewModelScope.launch {
            repository.addCropHistory(crop)
        }
    }
}
