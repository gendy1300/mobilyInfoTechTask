package com.gendy.bugIt.home.presentation

import com.gendy.bugIt.home.domain.model.BugsListModel

sealed class TicketsUiState {

    data object Loading : TicketsUiState()

    data class NetworkError(val errorMessage: String) : TicketsUiState()

    data class Success(val bugsList: List<BugsListModel>) : TicketsUiState()

}