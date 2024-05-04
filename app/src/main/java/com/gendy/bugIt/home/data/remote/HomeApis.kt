package com.gendy.bugIt.home.data.remote


import com.gendy.bugIt.addBug.data.model.AddBugRequest
import com.gendy.bugIt.home.data.model.OneSheetResponse
import com.gendy.bugIt.home.data.model.SpreadSheetResponseData
import com.gendy.bugIt.home.domain.model.BugsListModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface HomeApis {

    @GET("exec")
    suspend fun getBugData(
        @Query("action") action: String = "getAllData"
    ): List<BugsListModel>

    @GET("exec")
    suspend fun createSheet(
        @Query("action") action: String = "addNewSheet",
        @Query("sheetName") sheetName: String,
    ): String


}