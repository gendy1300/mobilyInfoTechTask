package com.gendy.bugIt.addBug.data.repository

import com.gendy.bugIt.addBug.data.remote.AddBugApis
import com.gendy.bugIt.addBug.domain.repositories.AddBugRepo
import com.gendy.bugIt.home.domain.model.BugsListModel
import com.gendy.bugIt.utils.retrofit.SafeApiCall
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

class AddABugRepoImp @Inject constructor(
    private var api: AddBugApis
) : AddBugRepo, SafeApiCall {
    override suspend fun uploadImage(image: File) = safeApiCall {

        val imageRequestBody = image.asRequestBody("image/*".toMediaTypeOrNull())
        val imagePart = MultipartBody.Part.createFormData("image", image.name, imageRequestBody)
        val apiKeyPart =
            MultipartBody.Part.createFormData("key", "8cdd5a04607e636a838cc39baeb90213")

        api.uploadImage(apiKeyPart, imagePart)
    }

    override suspend fun addABug(bugData: BugsListModel) = safeApiCall {
        var bugDataToGoogleSheetsData: List<String>

        bugData.apply {
            bugDataToGoogleSheetsData = listOf(title, description, photo, reporterName, date)
        }

        api.addBug(values = bugDataToGoogleSheetsData.joinToString(","))
    }


}