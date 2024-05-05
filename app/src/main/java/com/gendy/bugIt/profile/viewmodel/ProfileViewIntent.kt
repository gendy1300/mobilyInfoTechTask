package com.gendy.bugIt.profile.viewmodel

sealed class ProfileViewIntent {

    data class SaveReporterName(val name:String):ProfileViewIntent()
}