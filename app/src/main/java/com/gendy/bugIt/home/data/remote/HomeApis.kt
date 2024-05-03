package com.gendy.bugIt.home.data.remote


import retrofit2.http.GET
import retrofit2.http.Path

interface HomeApis {

    @GET("spreadsheets/{spreadsheetId}")
    suspend fun getBugData(
        @Path("spreadsheetId") sheetId: String
    ): String

}