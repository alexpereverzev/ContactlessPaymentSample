package com.example.contactless.payment.sample.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("settings")

class DataStoreManager(appContext: Context) {

    private val settingsDataStore = appContext.dataStore

    suspend fun saveTokens(tokens: Set<String>) {
        settingsDataStore.edit { settings ->
            settings[PaymentScheme.TOKENS] = tokens
        }
    }

    val tokens: Flow<Set<String>> = settingsDataStore.data.map { preferences ->
        preferences[PaymentScheme.TOKENS] ?: emptySet()
    }

}