package com.modul.buahhati.data.remote

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "Session")
class LoginPreference private constructor(private val dataStore: DataStore<Preferences>) {

    suspend fun login() {
        dataStore.edit { preferences ->
            preferences[LOGIN_KEY] = true
        }
    }

    fun getLoginStatus(): Flow<Boolean?> {
        return dataStore.data.map { preferences ->
            preferences[LOGIN_KEY] ?: false
        }
    }

    suspend fun logout() {
        dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = ""
            preferences[LOGIN_KEY] = false
            preferences[USER_NAME_KEY] = ""
        }
    }

    fun getToken(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[TOKEN_KEY]
        }
    }

    suspend fun svToken(token: String) {
        dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

    fun getUserId(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[USER_ID]
        }
    }

    fun getchildId(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[CHILD_ID]
        }
    }

    fun getAnalysisId(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[ANALYSIS_ID]
        }
    }

    suspend fun svUserId(userId: String) {
        dataStore.edit { preferences ->
            preferences[USER_ID] = userId
        }
    }

    fun getUserName(): Flow<String?> {
        return dataStore.data.map { preferences ->
            val userName = preferences[USER_NAME_KEY]
            Log.d("LoginPreference", "Retrieved User Name: $userName")
            userName
        }
    }

    suspend fun svUserName(userName: String) {
        dataStore.edit { preferences ->
            preferences[USER_NAME_KEY] = userName
            Log.d("LoginPreference", "Saved User Name: $userName")
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: LoginPreference? = null

        private val TOKEN_KEY = stringPreferencesKey("token")
        private val USER_ID = stringPreferencesKey("user_id")
        private val LOGIN_KEY = booleanPreferencesKey("is_login")
        private val USER_NAME_KEY = stringPreferencesKey("user_name")
        private val CHILD_ID = stringPreferencesKey("child_id")
        private val ANALYSIS_ID = stringPreferencesKey("analysis_id")

        fun getInstance(dataStore: DataStore<Preferences>): LoginPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = LoginPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}
