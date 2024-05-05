package com.gendy.bugIt.profile.viewmodel;

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gendy.bugIt.utils.navigation.AppNavigator
import com.gendy.bugIt.utils.preferences.PreferencesIntents
import com.gendy.bugIt.utils.preferences.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewmodel @Inject constructor(
    val appNavigator: AppNavigator,
    val preferenceManger: PreferencesManager
) : ViewModel() {

    var reporterName = mutableStateOf("")


    init {
        viewModelScope.launch {
            reporterName.value = preferenceManger.getReportedName()
        }
    }


    fun processIntent(intent: ProfileViewIntent) {
        when (intent) {
            is ProfileViewIntent.SaveReporterName -> saveReporterName(intent.name)

        }
    }


    fun saveReporterName(name: String) {
        viewModelScope.launch {
            preferenceManger.processIntent(
                PreferencesIntents.SaveReporterName(name)
            )

            appNavigator.navigateBack()
        }
    }
}