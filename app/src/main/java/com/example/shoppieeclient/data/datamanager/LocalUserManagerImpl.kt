package com.example.shoppieeclient.data.datamanager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.shoppieeclient.domain.auth.datamanager.LocalUserManager
import com.example.shoppieeclient.domain.common.model.UserDetails
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

    override suspend fun saveAppToken(token: String) {
        context.datastore.edit { prefs ->
            prefs[PreferencesKeys.TOKEN] = token
        }
    }

    override fun readAppToken(): Flow<String?> {
        return context.datastore.data.map { prefs ->
            prefs[PreferencesKeys.TOKEN]
        }
    }

    override suspend fun saveUserDetails(name: String, profileImage: String) {
        context.datastore.edit { prefs ->
            prefs[PreferencesKeys.USER_NAME] = name
            prefs[PreferencesKeys.USER_IMAGE] = profileImage
        }
    }

    override fun readUserDetails(): Flow<UserDetails> {
        return context.datastore.data.map { prefs ->
            UserDetails(
                prefs[PreferencesKeys.USER_NAME] ?: "",
                prefs[PreferencesKeys.USER_IMAGE] ?: ""
            )
        }
    }

//    override fun readUserImage(): Flow<String?> {
//        return context.datastore.data.map { prefs ->
//            prefs[PreferencesKeys.USER_IMAGE] ?: ""
//        }
//    }
//
//    override fun readUserName(): Flow<String?> {
//        return context.datastore.data.map { prefs ->
//            prefs[PreferencesKeys.USER_NAME] ?: ""
//        }
//    }


}

private val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = Constants.DATASTORE_NAME)


private object PreferencesKeys {
    val ONBOARDING_VALUE = booleanPreferencesKey(Constants.DATASTORE_ONBOARDING_KEY)
    val TOKEN = stringPreferencesKey(Constants.DATASTORE_TOKEN_KEY)
    val USER_NAME = stringPreferencesKey(Constants.USER_NAME)
    val USER_IMAGE = stringPreferencesKey(Constants.USER_IMAGE)
}