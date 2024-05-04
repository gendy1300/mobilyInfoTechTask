package com.gendy.bugIt.addBug.viewmodel;

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gendy.bugIt.addBug.domain.repositories.AddBugRepo
import com.gendy.bugIt.home.domain.model.BugsListModel
import com.gendy.bugIt.utils.navigation.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddBugViewmodel @Inject constructor(
    val appNavigator: AppNavigator,
    private val repo: AddBugRepo
) : ViewModel() {


    fun addABug(bugData: BugsListModel) {

        viewModelScope.launch {
            repo.addABug(bugData)
        }

    }


}

