package com.gendy.bugIt.utils.navigation.screens

import com.gendy.bugIt.R
import com.gendy.bugIt.utils.appendParams

sealed class HomeScreens {
    data object TicketsScreen : Destination.NoArgumentsDestination(TICKETS_SCREEN_ROUTE)


    data object TicketDetailsScreen : Destination(TICKET_DETAILS_SCREEN_ROUTE, TICKET_ID_KEY) {
        operator fun invoke(ticketId: String): String = route.appendParams(
            TICKET_ID_KEY to ticketId,
        )
    }

    companion object {
        const val ROOT_ROUTE = "home"
        val ROUTE_TITLE = R.string.tickets
        val FILLED_ROUTE_ICON = R.drawable.filled_home_icon
        val ROUTE_ICON = R.drawable.home_icon
        private const val TICKETS_SCREEN_ROUTE = "ticketsScreen"
        private const val TICKET_DETAILS_SCREEN_ROUTE = "ticketDetailsScreen"
        const val TICKET_ID_KEY = "ticketId"
    }
}

