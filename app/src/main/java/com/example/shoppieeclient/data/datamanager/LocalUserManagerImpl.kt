package com.example.shoppieeclient.data.datamanager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.shoppieeclient.domain.auth.datamanager.LocalUserManager
import com.example.shoppieeclient.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserManagerImpl(
    private val context: Context
): LocalUserManager {
    override suspend fun saveOnBoardingValue(value: Boolean) {
        context.datastore.edit { prefs ->
            prefs[PreferencesKeys.ONBOARDING_VALUE] = value
        }
    }

    override fun readOnBoardingValue(): Flow<Boolean> {
        return context.datastore.data.map { prefs ->
            prefs[PreferencesKeys.ONBOARDING_VALUE] ?: true
        }
    }

}

private val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = Constants.DATASTORE_NAME)


private object PreferencesKeys {
    val ONBOARDING_VALUE = booleanPreferencesKey(Constants.DATASTORE_ONBOARDING_KEY)
}