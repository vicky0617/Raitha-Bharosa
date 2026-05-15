package com.example.raithabharosahub.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.location.Geocoder
import androidx.lifecycle.AndroidViewModel
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.*

class LocationViewModel(application: Application) : AndroidViewModel(application) {
    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(application)
    private val geocoder = Geocoder(application, Locale.getDefault())

    private val _locationState = MutableStateFlow<LocationState>(LocationState.Idle)
    val locationState: StateFlow<LocationState> = _locationState

    @SuppressLint("MissingPermission")
    fun fetchCurrentLocation() {
        _locationState.value = LocationState.Loading
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                try {
                    val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)
                    if (!addresses.isNullOrEmpty()) {
                        val address = addresses[0]
                        val locationName = "${address.locality}, ${address.adminArea}"
                        _locationState.value = LocationState.Success(location.latitude, location.longitude, locationName)
                    } else {
                        _locationState.value = LocationState.Success(location.latitude, location.longitude, "Unknown Location")
                    }
                } catch (e: Exception) {
                    _locationState.value = LocationState.Success(location.latitude, location.longitude, "Unknown Location")
                }
            } else {
                _locationState.value = LocationState.Error("Could not fetch location")
            }
        }.addOnFailureListener {
            _locationState.value = LocationState.Error(it.message ?: "Unknown error")
        }
    }
}

sealed class LocationState {
    object Idle : LocationState()
    object Loading : LocationState()
    data class Success(val lat: Double, val lon: Double, val name: String) : LocationState()
    data class Error(val message: String) : LocationState()
}
