package com.example.raithabharosahub.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.raithabharosahub.data.local.AppDatabase
import com.example.raithabharosahub.data.local.UserPreferences
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class SettingsViewModel(application: Application) : AndroidViewModel(application) {
    private val userPreferences = UserPreferences(application)
    private val database = AppDatabase.getDatabase(application)

    private val _logoutEvent = MutableSharedFlow<Unit>()
    val logoutEvent: SharedFlow<Unit> = _logoutEvent

    fun logout() {
        viewModelScope.launch {
            userPreferences.clear()
            database.clearAllTables()
            _logoutEvent.emit(Unit)
        }
    }
}
