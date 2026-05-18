package com.example.raithabharosahub.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.raithabharosahub.data.DataGenerator
import com.example.raithabharosahub.data.DashboardData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<DashboardData?>(null)
    val uiState: StateFlow<DashboardData?> = _uiState.asStateFlow()

    init {
        refreshDashboardData()
    }

    fun refreshDashboardData() {
        viewModelScope.launch {
            // Simulates fetching database or live generator pulsing
            _uiState.value = DataGenerator.generateData()
        }
    }
}
