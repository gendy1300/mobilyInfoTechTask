package com.gendy.bugIt.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gendy.bugIt.home.domain.model.BugsListModel
import com.gendy.bugIt.home.domain.repositories.HomeRepo
import com.gendy.bugIt.home.presentation.TicketsUiState
import com.gendy.bugIt.utils.logDebug
import com.gendy.bugIt.utils.navigation.AppNavigator
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

    init {
        callGetSpreadSheet()
    }

    fun processIntent(intent: HomeViewIntent) {
        when (intent) {
            HomeViewIntent.GetBugData -> callGetSpreadSheet()
        }
    }


    private fun callGetSpreadSheet() {
        viewModelScope.launch {
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
                    val titlesList =
                        response.data.sheets?.mapNotNull { it.properties?.title } ?: listOf()

                    getTicketsFromOneSheet(titlesList)

                }
            }
        }
    }

    private fun getTicketsFromOneSheet(sheetTitles: List<String>) {
        viewModelScope.launch {
            val ticketsList = arrayListOf<BugsListModel>()
            sheetTitles.forEach {
                launch {
                    when (val response = repo.getDataFromSheet(it)) {
                        is ApiResult.Error -> {}
                        ApiResult.Loading -> {}
                        ApiResult.NoInternetConnection -> {}
                        is ApiResult.Success -> {
                            response.data.values?.drop(1)?.forEach { item ->
                                ticketsList.add(
                                    BugsListModel(
                                        id = item?.getOrNull(0) ?: "",
                                        title = item?.getOrNull(1) ?: "",
                                        description = item?.getOrNull(2) ?: "",
                                        imageUrl = item?.getOrNull(3) ?: "",
                                        reporterName = item?.getOrNull(4) ?: "",
                                        date = item?.getOrNull(5) ?: ""
                                    )
                                )
                            }
                        }
                    }
                }.join()

            }

            logDebug(ticketsList.joinToString(","))
        }

    }

}