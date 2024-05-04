package com.gendy.bugIt.addBug.domain.repositories

import com.gendy.bugIt.home.domain.model.BugsListModel
import com.gendy.bugIt.utils.retrofit.ApiResult
import java.io.File

interface AddBugRepo {

    suspend fun uploadImage(image: File)
    suspend fun addABug(bugData: BugsListModel):ApiResult<String>
}