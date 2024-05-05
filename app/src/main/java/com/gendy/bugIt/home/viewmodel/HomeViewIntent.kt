package com.gendy.bugIt.home.viewmodel

import com.gendy.bugIt.home.domain.model.BugsListModel

sealed class HomeViewIntent {

    data object GetBugData : HomeViewIntent()

    data class NavigateToBugDetails(val bugData:BugsListModel) : HomeViewIntent()


}
