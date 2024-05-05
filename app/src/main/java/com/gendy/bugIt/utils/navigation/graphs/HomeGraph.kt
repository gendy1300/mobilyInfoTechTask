package com.gendy.bugIt.utils.navigation.graphs

import androidx.compose.runtime.MutableState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.gendy.bugIt.home.presentation.TicketDetailsScreen
import com.gendy.bugIt.home.presentation.TicketsScreen
import com.gendy.bugIt.home.viewmodel.HomeViewmodel
import com.gendy.bugIt.utils.navigation.composable
import com.gendy.bugIt.utils.navigation.screens.HomeScreens
import com.gendy.bugIt.utils.navigation.sharedViewModel

fun NavGraphBuilder.homeGraph(
    navController: NavHostController,
    isBottomSheetVisibleState: MutableState<Boolean>,
    showSnackBar: (errorMessage: String?) -> Unit,
    showLoading: (show: Boolean) -> Unit
) {
    navigation(
        startDestination = HomeScreens.TicketsScreen.fullRoute, route = HomeScreens.ROOT_ROUTE
    ) {


        composable(HomeScreens.TicketsScreen) { entry ->
            isBottomSheetVisibleState.value = true
            val viewModel = entry.sharedViewModel<HomeViewmodel>(navController)
            TicketsScreen(
                showLoading = showLoading,
                showSnackBar = showSnackBar,
                viewmodel = viewModel
            )
        }

        composable(HomeScreens.TicketDetailsScreen) { entry ->
            isBottomSheetVisibleState.value = false
            val viewModel = entry.sharedViewModel<HomeViewmodel>(navController)
            TicketDetailsScreen(viewModel)

        }


    }
}