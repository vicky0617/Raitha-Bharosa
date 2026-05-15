package com.example.raithabharosahub.data.local

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "settings")

class UserPreferences(private val context: Context) {
    private val LANGUAGE_KEY = stringPreferencesKey("language")
    private val IS_REGISTERED_KEY = booleanPreferencesKey("is_registered")

    val languageFlow: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[LANGUAGE_KEY] ?: "en"
    }

    val isRegisteredFlow: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[IS_REGISTERED_KEY] ?: false
    }

    suspend fun saveLanguage(language: String) {
        context.dataStore.edit { preferences ->
            preferences[LANGUAGE_KEY] = language
        }
    }

    suspend fun setRegistered(isRegistered: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_REGISTERED_KEY] = isRegistered
        }
    }

    suspend fun clear() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}
