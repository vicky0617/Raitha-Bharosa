package com.example.raithabharosahub.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.raithabharosahub.data.remote.RetrofitInstance
import com.example.raithabharosahub.data.remote.WeatherResponse
import com.example.raithabharosahub.data.repository.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    private val repository = WeatherRepository(RetrofitInstance.api)

    private val _weatherState = MutableStateFlow<WeatherState>(WeatherState.Loading)
    val weatherState: StateFlow<WeatherState> = _weatherState

    fun fetchWeather(lat: Double, lon: Double) {
        viewModelScope.launch {
            _weatherState.value = WeatherState.Loading
            repository.getCurrentWeather(lat, lon).onSuccess {
                _weatherState.value = WeatherState.Success(it)
            }.onFailure {
                _weatherState.value = WeatherState.Error(it.message ?: "Unknown error")
            }
        }
    }
}

sealed class WeatherState {
    object Loading : WeatherState()
    data class Success(val data: WeatherResponse) : WeatherState()
    data class Error(val message: String) : WeatherState()
}
