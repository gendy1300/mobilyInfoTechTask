package com.gendy.bugIt.home.data.remote


import com.gendy.bugIt.home.data.model.OneSheetResponse
import com.gendy.bugIt.home.data.model.SpreadSheetResponseData
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeApis {

    @GET("spreadsheets/{spreadsheetId}")
    suspend fun getBugData(
        @Path("spreadsheetId") sheetId: String
    ): SpreadSheetResponseData

    @GET("spreadsheets/{spreadsheetId}/values/{sheetName}")
    suspend fun getASpecificSheet(
        @Path("spreadsheetId") sheetId: String,
        @Path("sheetName") sheetName: String
    ): OneSheetResponse



}