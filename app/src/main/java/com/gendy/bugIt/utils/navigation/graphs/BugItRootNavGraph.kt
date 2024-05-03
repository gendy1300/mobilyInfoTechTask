package com.gendy.bugIt.utils.navigation.graphs

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.gendy.bugIt.utils.navigation.NavHost
import com.gendy.bugIt.utils.navigation.screens.Destination
import com.gendy.bugIt.utils.showSnackBar


@Composable
fun bugItRootNavGraph(
    navController: NavHostController,
    isBottomBarVisible: MutableState<Boolean>,
    snackBarHostState: SnackbarHostState,
): @Composable (PaddingValues) -> Unit {

    var loading by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    return { scaffoldPadding ->

        NavHost(
            navController = navController,
            startDestination = Destination.HomeGraph,
            Modifier
                .fillMaxSize()
                .padding(bottom = if (isBottomBarVisible.value) scaffoldPadding.calculateBottomPadding() else 0.dp),

            ) {


            homeGraph(isBottomSheetVisibleState = isBottomBarVisible, showSnackBar = { message ->
                showSnackBar(scope = scope, snackBarHost = snackBarHostState, message = message)
            }, showLoading = { show: Boolean ->
                loading = show
            }, navController = navController)


        }
    }
}