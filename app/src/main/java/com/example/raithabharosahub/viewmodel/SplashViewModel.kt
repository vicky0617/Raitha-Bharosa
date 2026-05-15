package com.example.raithabharosahub.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.raithabharosahub.data.local.UserPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SplashViewModel(application: Application) : AndroidViewModel(application) {
    private val userPreferences = UserPreferences(application)

    private val _isRegistered = MutableStateFlow<Boolean?>(null)
    val isRegistered: StateFlow<Boolean?> = _isRegistered

    init {
        checkRegistrationStatus()
    }

    private fun checkRegistrationStatus() {
        viewModelScope.launch {
            val registered = userPreferences.isRegisteredFlow.first()
            _isRegistered.value = registered
        }
    }
}
