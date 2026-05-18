package com.example.raithabharosahub.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class ProfileState(
    val farmerName: String = "Malleshappa",
    val primaryCrop: String = "Wheat",
    val location: String = "Dharwad, Karnataka",
    val phoneNumber: String = "+91 9876543210",
    val isEditing: Boolean = false // Tracks if fields are editable
)

class ProfileViewModel : ViewModel() {
    private val _state = MutableStateFlow(ProfileState())
    val state: StateFlow<ProfileState> = _state.asStateFlow()

    fun toggleEditMode() {
        _state.update { it.copy(isEditing = !it.isEditing) }
    }

    fun updateName(newName: String) {
        _state.update { it.copy(farmerName = newName) }
    }

    fun updateCrop(newCrop: String) {
        _state.update { it.copy(primaryCrop = newCrop) }
    }

    fun updateLocation(newLocation: String) {
        _state.update { it.copy(location = newLocation) }
    }

    fun updatePhone(newPhone: String) {
        _state.update { it.copy(phoneNumber = newPhone) }
    }
}
