package com.gendy.bugIt.addBug.viewmodel;

import androidx.lifecycle.ViewModel
import com.gendy.bugIt.addBug.domain.repositories.AddBugRepo
import com.gendy.bugIt.utils.navigation.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddBugViewmodel @Inject constructor(
    val appNavigator: AppNavigator,
    private val repo: AddBugRepo
) : ViewModel() {










}

