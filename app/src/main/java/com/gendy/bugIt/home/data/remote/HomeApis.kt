package com.gendy.bugIt.home.data.remote


import com.gendy.bugIt.home.domain.model.BugsListModel
import retrofit2.http.GET
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