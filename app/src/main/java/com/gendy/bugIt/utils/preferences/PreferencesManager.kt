package com.gendy.bugIt.utils.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.gendy.bugIt.activities.userDataStore
import com.gendy.bugIt.utils.preferences.PreferencesManager.UserPreferenceKey.REPORTER_KEY
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesManager @Inject constructor(val context: Context) {


    private var userDataStore: DataStore<Preferences> = context.userDataStore

    suspend fun processIntent(intent: PreferencesIntents) {
        when (intent) {
            is PreferencesIntents.SaveReporterName -> {
                userDataStore.edit { preferences ->
                    preferences[REPORTER_KEY] = intent.reporterName
                }
            }
        }
    }


    suspend fun getReportedName(): String {
        val preferences = userDataStore.data.first()
        return preferences[REPORTER_KEY] ?: "user1"
    }


    private object UserPreferenceKey {
        val REPORTER_KEY = stringPreferencesKey("reporterName")
    }

}