package com.gendy.bugIt.home.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gendy.bugIt.home.domain.repositories.HomeRepo
import com.gendy.bugIt.home.presentation.TicketsUiState
import com.gendy.bugIt.utils.createEmptyBugModel
import com.gendy.bugIt.utils.createTodayDate
import com.gendy.bugIt.utils.navigation.AppNavigator
import com.gendy.bugIt.utils.navigation.screens.HomeScreens
import com.gendy.bugIt.utils.retrofit.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewmodel @Inject constructor(
    val appNavigator: AppNavigator,
    private val repo: HomeRepo
) : ViewModel() {

    private val _ticketsUiState = MutableStateFlow<TicketsUiState>(TicketsUiState.Loading)
    val ticketsUiState = _ticketsUiState.asStateFlow()


    val selectedBugData = mutableStateOf(createEmptyBugModel())


    fun processIntent(intent: HomeViewIntent) {
        when (intent) {
            HomeViewIntent.GetBugData -> callGetBugs()

            is HomeViewIntent.NavigateToBugDetails -> {
                selectedBugData.value = intent.bugData

                appNavigator.tryNavigateTo(HomeScreens.TicketDetailsScreen())
            }
        }
    }


    private fun callGetBugs() {

        viewModelScope.launch {

            repo.createASheetTabWithDate(createTodayDate())

            when (val response = repo.getBugData()) {
                is ApiResult.Error -> {
                    _ticketsUiState.value = TicketsUiState.NetworkError(response.message.toString())
                }

                ApiResult.Loading -> {
                    _ticketsUiState.value = TicketsUiState.Loading
                }

                ApiResult.NoInternetConnection -> {
                    _ticketsUiState.value = TicketsUiState.NetworkError("No internet Connection")
                }

                is ApiResult.Success -> {
                    _ticketsUiState.value = TicketsUiState.Success(response.data)
                }
            }
        }
    }


}