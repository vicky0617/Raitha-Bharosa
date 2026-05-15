package com.example.raithabharosahub.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.raithabharosahub.data.local.AppDatabase
import com.example.raithabharosahub.data.local.UserPreferences
import com.example.raithabharosahub.data.model.FarmerProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

import kotlinx.coroutines.flow.first

class RegistrationViewModel(application: Application) : AndroidViewModel(application) {
    private val farmerDao = AppDatabase.getDatabase(application).farmerDao()
    private val userPreferences = UserPreferences(application)

    private val _registrationState = MutableStateFlow<RegistrationState>(RegistrationState.Idle)
    val registrationState: StateFlow<RegistrationState> = _registrationState

    fun registerFarmer(fullName: String, mobileNumber: String, village: String, district: String, primaryCrop: String) {
        if (fullName.isBlank() || mobileNumber.isBlank() || village.isBlank() || district.isBlank() || primaryCrop.isBlank()) {
            _registrationState.value = RegistrationState.Error("Please fill all fields")
            return
        }

        if (mobileNumber.length < 10) {
            _registrationState.value = RegistrationState.Error("Please enter a valid mobile number")
            return
        }

        viewModelScope.launch {
            _registrationState.value = RegistrationState.Loading
            try {
                val currentLanguage = userPreferences.languageFlow.first()
                val profile = FarmerProfile(
                    fullName = fullName,
                    mobileNumber = mobileNumber,
                    village = village,
                    district = district,
                    primaryCrop = primaryCrop,
                    selectedLanguage = currentLanguage
                )
                farmerDao.insertFarmerProfile(profile)
                userPreferences.setRegistered(true)
                _registrationState.value = RegistrationState.Success
            } catch (e: Exception) {
                _registrationState.value = RegistrationState.Error(e.message ?: "Failed to save profile")
            }
        }
    }

    fun clearError() {
        _registrationState.value = RegistrationState.Idle
    }
}

sealed class RegistrationState {
    object Idle : RegistrationState()
    object Loading : RegistrationState()
    object Success : RegistrationState()
    data class Error(val message: String) : RegistrationState()
}
