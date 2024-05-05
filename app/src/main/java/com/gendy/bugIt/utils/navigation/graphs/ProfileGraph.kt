package com.gendy.bugIt.utils.navigation.graphs

import androidx.compose.runtime.MutableState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.gendy.bugIt.profile.presentation.ProfileScreen
import com.gendy.bugIt.utils.navigation.composable
import com.gendy.bugIt.utils.navigation.screens.ProfileScreens

fun NavGraphBuilder.profileGraph(
    isBottomSheetVisibleState: MutableState<Boolean>,
    showLoading: (Boolean) -> Unit,
) {
    navigation(
        startDestination = ProfileScreens.MoreScreen.fullRoute, route = ProfileScreens.ROOT_ROUTE
    ) {
        composable(ProfileScreens.MoreScreen) { entry ->
            isBottomSheetVisibleState.value = true
            ProfileScreen(showLoading = showLoading)
        }
    }
}