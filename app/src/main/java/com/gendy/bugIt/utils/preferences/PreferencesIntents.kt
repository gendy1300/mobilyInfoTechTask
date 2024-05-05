package com.gendy.bugIt.utils.preferences

sealed class PreferencesIntents {

    data class SaveReporterName(val reporterName: String) : PreferencesIntents()

}