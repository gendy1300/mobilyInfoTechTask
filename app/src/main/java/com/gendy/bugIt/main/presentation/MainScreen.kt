package com.gendy.bugIt.main.presentation

import android.app.Activity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.gendy.bugIt.R
import com.gendy.bugIt.main.viewmodel.MainViewModel
import com.gendy.bugIt.utils.BugItText
import com.gendy.bugIt.utils.SnackBarLayout
import com.gendy.bugIt.utils.navigation.BottomNavigationItems
import com.gendy.bugIt.utils.navigation.NavigationIntent
import com.gendy.bugIt.utils.navigation.graphs.bugItRootNavGraph
import com.gendy.bugIt.utils.theme.BlueColor
import com.gendy.bugIt.utils.theme.TextUnselectedColor
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.receiveAsFlow


val bottomNavigationItems = listOf(
    BottomNavigationItems.Home, BottomNavigationItems.Home
)


@Composable
fun MainScreen(
    mainViewModel: MainViewModel = hiltViewModel()
) {


    val snackBarHostState = remember { SnackbarHostState() }
    val navController = rememberNavController()


    val isBottomBarVisible = remember {
        mutableStateOf(false)
    }

    NavigationEffects(
        navigationChannel = mainViewModel.navigationChannel, navHostController = navController
    )


    Scaffold(modifier = Modifier
        .fillMaxSize()
        .imePadding(), snackbarHost = {
        SnackbarHost(
            hostState = snackBarHostState, modifier = Modifier.padding(16.dp)
        ) {
            SnackBarLayout(it.visuals.message)
        }
    }, bottomBar = {
        AnimatedVisibility(
            visible = isBottomBarVisible.value,
            enter = slideInVertically(initialOffsetY = { it }),
            exit = slideOutVertically(targetOffsetY = { it }),
        ) {
            BottomNav()
        }


    }, content = bugItRootNavGraph(
        navController,
        isBottomBarVisible,
        snackBarHostState,
    )
    )

}


@Composable
fun NavigationEffects(
    navigationChannel: Channel<NavigationIntent>, navHostController: NavHostController
) {
    val activity = (LocalContext.current as? Activity)
    LaunchedEffect(activity, navHostController, navigationChannel) {
        navigationChannel.receiveAsFlow().collect { intent ->
            if (activity?.isFinishing == true) {
                return@collect
            }
            when (intent) {
                is NavigationIntent.NavigateBack -> {
                    if (intent.route != null) {
                        navHostController.popBackStack(intent.route, intent.inclusive)
                    } else {
                        navHostController.popBackStack()
                    }
                }

                is NavigationIntent.NavigateTo -> {
                    navHostController.navigate(intent.route) {
                        launchSingleTop = intent.isSingleTop
                        intent.popUpToRoute?.let { popUpToRoute ->
                            popUpTo(popUpToRoute) { inclusive = intent.inclusive }
                        }
                    }
                }

            }
        }
    }
}


// Using this function will result in hiding the ripple effect in clicks
object NoRippleInteractionSource : MutableInteractionSource {

    override val interactions: Flow<Interaction> = emptyFlow()
    override suspend fun emit(interaction: Interaction) {}
    override fun tryEmit(interaction: Interaction) = true
}

@Composable
fun BottomNav() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()


    BottomAppBar(modifier = Modifier
        .height(
            50.dp + WindowInsets.systemBars
                .asPaddingValues()
                .calculateBottomPadding()
        )
        .fillMaxWidth(), floatingActionButton = {
        FloatingActionButton(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(8.dp),
            backgroundColor = BlueColor
        ) {
            Icon(
                painter = painterResource(id = R.drawable.plus_icon),
                contentDescription = null,

                )
        }
    }, actions = {

        val currentDestination = navBackStackEntry?.destination

        bottomNavigationItems.forEach { bottomNavigationItem ->
            val selected =
                currentDestination?.hierarchy?.any { it.route == bottomNavigationItem.route } == true
            BottomNavigationItem(interactionSource = NoRippleInteractionSource, icon = {
                Icon(
                    painter = painterResource(id = if (selected) bottomNavigationItem.filledIcon else bottomNavigationItem.icon),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }, label = {
                BugItText(
                    text = stringResource(bottomNavigationItem.resourceId),
                    fontSize = 8.sp,
                    color = if (!selected) TextUnselectedColor else Color.Black,
                    maxLines = 1
                )
            }, selected = selected, onClick = {
                navController.navigate(bottomNavigationItem.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }

                    launchSingleTop = true

                    restoreState = true
                }
            })
        }
    }

    )

}

@Preview
@Composable
private fun preview() {
    BottomNav()
}


