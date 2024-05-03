package com.gendy.bugIt.utils.navigation.graphs

import androidx.compose.runtime.MutableState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.gendy.bugIt.home.presentation.TicketsScreen
import com.gendy.bugIt.utils.navigation.composable
import com.gendy.bugIt.utils.navigation.screens.HomeScreens

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
            TicketsScreen()
        }

        composable(HomeScreens.TicketDetailsScreen) { entry ->
            isBottomSheetVisibleState.value = false

            val ticketId = entry.arguments?.getString(HomeScreens.TICKET_ID_KEY)

        }


    }
}