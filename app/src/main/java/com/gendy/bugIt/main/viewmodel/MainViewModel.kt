package com.gendy.bugIt.main.viewmodel

import androidx.lifecycle.ViewModel
import com.gendy.bugIt.utils.navigation.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    appNavigator: AppNavigator,
) : ViewModel() {

    val navigationChannel = appNavigator.navigationChannel

}
