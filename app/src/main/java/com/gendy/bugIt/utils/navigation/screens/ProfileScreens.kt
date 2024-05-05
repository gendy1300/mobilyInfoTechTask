package com.gendy.bugIt.utils.navigation.screens

import com.gendy.bugIt.R

sealed class ProfileScreens {

    data object MoreScreen : Destination.NoArgumentsDestination(MORE_SCREEN_ROUTE)


    companion object {
        const val ROOT_ROUTE = "profile"
        val ROUTE_TITLE = R.string.profile
        val FILLED_ROUTE_ICON = R.drawable.filled_more_icon
        val ROUTE_ICON = R.drawable.more_icon
        private const val MORE_SCREEN_ROUTE = "moreScreen"

    }
}