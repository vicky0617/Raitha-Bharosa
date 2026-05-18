package com.example.raithabharosahub.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.raithabharosahub.data.ActivityDao
import com.example.raithabharosahub.data.KrishiActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CalendarViewModel(private val activityDao: ActivityDao) : ViewModel() {

    // Tracks selected day string. Defaults to "15" based on your UI screenshot
    private val _selectedDate = MutableStateFlow("15")
    val selectedDate: StateFlow<String> = _selectedDate.asStateFlow()

    // Dynamically emits tasks whenever the user clicks a different date
    @OptIn(ExperimentalCoroutinesApi::class)
    val currentActivities: StateFlow<List<KrishiActivity>> = _selectedDate
        .flatMapLatest { date -> activityDao.getActivitiesForDate(date) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun selectDate(date: String) {
        _selectedDate.value = date
    }

    fun addNewMockActivity() {
        viewModelScope.launch {
            // Adds a default activity for demonstration when the "+" FAB is pressed
            val mockTask = KrishiActivity(
                dateString = _selectedDate.value,
                title = "Custom Field Check",
                time = "12:00 PM",
                category = "Monitoring"
            )
            activityDao.insertActivity(mockTask)
        }
    }
}
