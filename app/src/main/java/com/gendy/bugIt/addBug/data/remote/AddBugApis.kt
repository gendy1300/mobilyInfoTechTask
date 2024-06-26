package com.gendy.bugIt.addBug.data.remote

import com.gendy.bugIt.addBug.data.model.ImageUploadResponse
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query
import retrofit2.http.Url

interface AddBugApis {


    @Multipart
    @POST
    suspend fun uploadImage(
        @Part apiKey: MultipartBody.Part,
        @Part image: MultipartBody.Part,
        @Url url: String = "https://api.imgbb.com/1/upload"
    ): ImageUploadResponse

    @GET("exec")
    suspend fun addBug(
        @Query("action") action: String = "insertValue",
        @Query("values",encoded = true) values: String //should be comma separated
    ): String
}