package com.gendy.bugIt.addBug.viewmodel;

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gendy.bugIt.addBug.domain.model.AddBugScreenFields
import com.gendy.bugIt.addBug.domain.repositories.AddBugRepo
import com.gendy.bugIt.addBug.presentation.AddBugUiState
import com.gendy.bugIt.home.domain.model.BugsListModel
import com.gendy.bugIt.utils.createTodayDate
import com.gendy.bugIt.utils.getImageFile
import com.gendy.bugIt.utils.navigation.AppNavigator
import com.gendy.bugIt.utils.navigation.screens.AddBugScreens
import com.gendy.bugIt.utils.navigation.screens.HomeScreens
import com.gendy.bugIt.utils.preferences.PreferencesManager
import com.gendy.bugIt.utils.retrofit.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddBugViewmodel @Inject constructor(
    private val appNavigator: AppNavigator,
    private val repo: AddBugRepo,
    private val preferenceManger: PreferencesManager,
) : ViewModel() {


    var addBugFields = mutableStateOf(AddBugScreenFields())

    private val _addBugUiState = MutableStateFlow<AddBugUiState>(
        AddBugUiState.Idle(addBugFields.value)
    )
    val addBugUiState = _addBugUiState.asStateFlow()

    fun processIntent(intent: AddBugViewIntent) {
        when (intent) {

            AddBugViewIntent.UploadBug -> addABug()
            is AddBugViewIntent.GetImage -> getCurrentImageModel(
                currentActivity = intent.currentActivity,
                lifecycleOwner = intent.lifecycleOwner,
                context = intent.context
            )
        }
    }


    private fun addABug() {
        viewModelScope.launch {
            _addBugUiState.value = AddBugUiState.UploadingImage

            addBugFields.value.photoFile?.let {
                when (val response = repo.uploadImage(it)) {

                    is ApiResult.Error -> _addBugUiState.value =
                        AddBugUiState.NetworkError(response.message.toString())

                    ApiResult.Loading -> _addBugUiState.value = AddBugUiState.UploadingImage

                    ApiResult.NoInternetConnection -> _addBugUiState.value =
                        AddBugUiState.NetworkError("No internet Connection")

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
                    reporterName = preferenceManger.getReportedName(),
                    date = createTodayDate()
                )
            }

            when (val response = repo.addABug(bugData)) {

                is ApiResult.Error -> _addBugUiState.value =
                    AddBugUiState.NetworkError(response.message.toString())

                ApiResult.Loading -> _addBugUiState.value = AddBugUiState.CreatingABug

                ApiResult.NoInternetConnection -> _addBugUiState.value =
                    AddBugUiState.NetworkError("No internet Connection")

                is ApiResult.Success -> {
                    addBugFields.value = AddBugScreenFields()

                    appNavigator.navigateBack(AddBugScreens.AddBugScreen(),inclusive = true)
                    appNavigator.navigateTo(HomeScreens.ROOT_ROUTE)

                }
            }
        }

    }

    private fun getCurrentImageModel(
        lifecycleOwner: LifecycleOwner,
        currentActivity: ComponentActivity?,
        context: Context
    ) {
        lifecycleOwner.lifecycle.addObserver(LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME && currentActivity is Activity) {
                val intent = currentActivity.intent
                if (intent != null && intent.action == Intent.ACTION_SEND) {

                    val imageUri = intent.getParcelableExtra<Uri>(Intent.EXTRA_STREAM)

                    if (imageUri != null) {

                        addBugFields.value =
                            addBugFields.value.copy(photoFile = getImageFile(imageUri, context))
                    }
                }
            }

        })
    }
}



