package com.gendy.bugIt.activities

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.gendy.bugIt.main.presentation.MainScreen
import com.gendy.bugIt.utils.theme.BugItTheme
import dagger.hilt.android.AndroidEntryPoint


val Context.userDataStore: DataStore<Preferences> by preferencesDataStore(name = "user")

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        enableEdgeToEdge()

        setContent {
            BugItTheme {
                MainScreen()
            }
        }
    }
}