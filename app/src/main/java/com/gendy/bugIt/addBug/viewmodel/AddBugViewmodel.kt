package com.gendy.bugIt.addBug.viewmodel;

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gendy.bugIt.addBug.domain.model.AddBugScreenFields
import com.gendy.bugIt.addBug.domain.repositories.AddBugRepo
import com.gendy.bugIt.addBug.presentation.AddBugUiState
import com.gendy.bugIt.home.domain.model.BugsListModel
import com.gendy.bugIt.utils.createTodayDate
import com.gendy.bugIt.utils.navigation.AppNavigator
import com.gendy.bugIt.utils.retrofit.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddBugViewmodel @Inject constructor(
    val appNavigator: AppNavigator,
    private val repo: AddBugRepo
) : ViewModel() {


    val addBugFields = mutableStateOf(AddBugScreenFields())

    private val _addBugUiState = MutableStateFlow<AddBugUiState>(
        AddBugUiState.Idle(addBugFields.value)
    )
    val addBugUiState = _addBugUiState.asStateFlow()

    fun processIntent(intent: AddBugViewIntent) {
        when (intent) {

            AddBugViewIntent.UploadBug -> addABug()
        }
    }


    private fun addABug() {
        viewModelScope.launch {

            addBugFields.value.photoFile?.let {
                when (val response = repo.uploadImage(it)) {
                    is ApiResult.Error -> AddBugUiState.NetworkError(response.message.toString())
                    ApiResult.Loading -> AddBugUiState.UploadingImage

                    ApiResult.NoInternetConnection -> AddBugUiState.NetworkError("No internet Connection")
                    is ApiResult.Success -> {
                        response.data.data?.image?.url?.let { imageUrl ->
                            uploadBugToGoogleSheets(
                                imageUrl
                            )
                        }
                    }
                }
            }

        }

    }


    private fun uploadBugToGoogleSheets(imageUrl: String) {
        viewModelScope.launch {
            val bugData: BugsListModel
            _addBugUiState.value = AddBugUiState.CreatingABug

            addBugFields.value.apply {
                bugData = BugsListModel(
                    title = title.toString(),
                    description = description.toString(),
                    photo = imageUrl,
                    reporterName = "user 1",
                    date = createTodayDate()
                )
            }

            when (val response = repo.addABug(bugData)) {

                is ApiResult.Error -> AddBugUiState.NetworkError(response.message.toString())

                ApiResult.Loading -> AddBugUiState.CreatingABug

                ApiResult.NoInternetConnection -> AddBugUiState.NetworkError("No internet Connection")
                is ApiResult.Success -> {

                }
            }
        }

    }
}



