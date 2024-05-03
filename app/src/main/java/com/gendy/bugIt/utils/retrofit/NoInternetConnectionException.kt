package com.gendy.bugIt.utils.retrofit

import okio.IOException

class NoInternetConnectionException : IOException() {
    override val message: String
        get() = "You are offline"
}