package com.gendy.bugIt.home.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.gendy.bugIt.home.viewmodel.HomeViewmodel
import com.gendy.bugIt.utils.BugItText


@Composable
fun TicketsScreen(
    viewmodel: HomeViewmodel = hiltViewModel()
) {
    BugItText(text = "fwhbdiusbdi")
}