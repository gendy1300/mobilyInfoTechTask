package com.gendy.bugIt.utils

import android.util.Log
import androidx.compose.material3.SnackbarHostState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

internal fun String.appendParams(vararg params: Pair<String, Any?>): String {
    val builder = StringBuilder(this)

    params.forEach {
        it.second?.toString()?.let { arg ->
            builder.append("/$arg")
        }
    }

    return builder.toString()
}


fun showSnackBar(scope: CoroutineScope, snackBarHost: SnackbarHostState, message: String?) {
    scope.launch {
        snackBarHost.showSnackbar(message = message ?: "An Error Has Occurred")
    }
}


fun logDebug(message: String, tag: String = "BugIt") {
    Log.d(tag, message)
}


fun String.checkIfItHasTodaySheet(): Boolean {
    val inputFormat = DateTimeFormatter.ofPattern("yyyy/M/d")
    return try {

        val date = LocalDate.parse(this, inputFormat)
        val today = LocalDate.now()

        date.isEqual(today) || date.isAfter(today)

    } catch (_: Exception) {
        false
    }
}

fun createTodayDate(): String {
    val currentDateTime = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy/M/d")
    return currentDateTime.format(formatter)
}