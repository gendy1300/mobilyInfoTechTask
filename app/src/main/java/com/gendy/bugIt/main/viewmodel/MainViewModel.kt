package com.gendy.bugIt.main.viewmodel

import androidx.lifecycle.ViewModel
import com.gendy.bugIt.utils.navigation.AppNavigator
import com.gendy.bugIt.utils.navigation.screens.AddBugScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val appNavigator: AppNavigator,
) : ViewModel() {

    val navigationChannel = appNavigator.navigationChannel


    fun processIntent(intent: MainViewIntents) {
        when (intent) {
            MainViewIntents.NavigateToAddBug -> appNavigator.tryNavigateTo(AddBugScreens.ROOT_ROUTE)
        }
    }

}
