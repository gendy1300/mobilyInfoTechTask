package com.gendy.bugIt.utils.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.gendy.bugIt.utils.navigation.screens.FavoritesScreens
import com.gendy.bugIt.utils.navigation.screens.HomeScreens
import com.gendy.bugIt.utils.navigation.screens.InsightsScreens
import com.gendy.bugIt.utils.navigation.screens.MoreScreens
import com.gendy.bugIt.utils.navigation.screens.NotificationsScreens

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

    data object Insights : BottomNavigationItems(
        InsightsScreens.ROOT_ROUTE,
        InsightsScreens.ROUTE_TITLE,
        InsightsScreens.FILLED_ROUTE_ICON,
        InsightsScreens.ROUTE_ICON
    )

    data object Favorites : BottomNavigationItems(
        FavoritesScreens.ROOT_ROUTE,
        FavoritesScreens.ROUTE_TITLE,
        FavoritesScreens.FILLED_ROUTE_ICON,
        FavoritesScreens.ROUTE_ICON
    )

    data object Notifications : BottomNavigationItems(
        NotificationsScreens.ROOT_ROUTE,
        NotificationsScreens.ROUTE_TITLE,
        NotificationsScreens.FILLED_ROUTE_ICON,
        NotificationsScreens.ROUTE_ICON
    )

    data object More : BottomNavigationItems(
        MoreScreens.ROOT_ROUTE,
        MoreScreens.ROUTE_TITLE,
        MoreScreens.FILLED_ROUTE_ICON,
        MoreScreens.ROUTE_ICON
    )

}