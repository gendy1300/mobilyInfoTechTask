package com.gendy.bugIt.utils.navigation.screens

class AddBugScreens {
    data object AddBugScreen : Destination.NoArgumentsDestination(ADD_BUG_SCREEN)

    companion object {
        const val ROOT_ROUTE = "addBug"
        private const val ADD_BUG_SCREEN = "addBugScreen"
    }
}