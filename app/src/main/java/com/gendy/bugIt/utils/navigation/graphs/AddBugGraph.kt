package com.gendy.bugIt.utils.navigation.graphs

import androidx.compose.runtime.MutableState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.gendy.bugIt.addBug.presentation.AddBugScreen
import com.gendy.bugIt.utils.navigation.composable
import com.gendy.bugIt.utils.navigation.screens.AddBugScreens

fun NavGraphBuilder.addBugGraph(
    navController: NavHostController,
    isBottomSheetVisibleState: MutableState<Boolean>,
    showSnackBar: (errorMessage: String?) -> Unit,
    showLoading: (show: Boolean) -> Unit
) {
    navigation(
        startDestination = AddBugScreens.AddBugScreen.fullRoute, route = AddBugScreens.ROOT_ROUTE
    ) {


        composable(AddBugScreens.AddBugScreen) { entry ->
            isBottomSheetVisibleState.value = true
            AddBugScreen(showSnackBar = showSnackBar, showLoading = showLoading)
        }

    }
}