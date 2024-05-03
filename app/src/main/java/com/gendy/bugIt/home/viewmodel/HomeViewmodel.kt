package com.gendy.bugIt.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gendy.bugIt.home.domain.repositories.HomeRepo
import com.gendy.bugIt.utils.navigation.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewmodel @Inject constructor(
    val appNavigator: AppNavigator,
    private val repo: HomeRepo
) : ViewModel() {

    init {
        getBugData()
    }

    fun processIntent(intent: HomeViewIntent) {
        when (intent) {
            HomeViewIntent.GetBugData -> getBugData()
        }
    }


    private fun getBugData() {
        viewModelScope.launch {
            repo.getBugData()
        }

    }
}