package com.gendy.bugIt.utils.navigation.screens

sealed class Destination(
    protected val route: String,
    vararg params: String,
    isBottomBarEnabled: Boolean = true
) {
    val fullRoute: String = if (params.isEmpty()) route else {
        val builder = StringBuilder(route)
        params.forEach { builder.append("/{${it}}") }
        builder.toString()
    }

    sealed class NoArgumentsDestination(route: String, isBottomBarEnabled: Boolean = true) :
        Destination(route, isBottomBarEnabled = isBottomBarEnabled) {
        operator fun invoke(): String = route
    }


    data object HomeGraph : NoArgumentsDestination(HOME_ROUTE, true)



    companion object {
        private const val HOME_ROUTE = HomeScreens.ROOT_ROUTE


    }
}

