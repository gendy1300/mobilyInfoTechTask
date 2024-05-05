package com.gendy.bugIt.addBug.presentation

import com.gendy.bugIt.addBug.domain.model.AddBugScreenFields
import com.gendy.bugIt.home.domain.model.BugsListModel

sealed class AddBugUiState {

    data class Idle(val fields: AddBugScreenFields) : AddBugUiState()

    data class NetworkError(val errorMessage: String) : AddBugUiState()

    data object UploadingImage : AddBugUiState()

    data object CreatingABug : AddBugUiState()

}