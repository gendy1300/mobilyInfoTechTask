package com.gendy.bugIt.utils.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.gendy.bugIt.utils.navigation.screens.HomeScreens

sealed class BottomNavigationItems(
    val route: String,
    @StringRes val resourceId: Int,
    @DrawableRes val filledIcon: Int,
    @DrawableRes val icon: Int,
) {

    data object Home : BottomNavigationItems(
        HomeScreens.ROOT_ROUTE,
        HomeScreens.ROUTE_TITLE,
        HomeScreens.FILLED_ROUTE_ICON,
        HomeScreens.ROUTE_ICON
    )
}