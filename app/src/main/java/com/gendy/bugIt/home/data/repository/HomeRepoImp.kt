package com.gendy.bugIt.home.data.repository

import com.gendy.bugIt.home.data.remote.HomeApis
import com.gendy.bugIt.home.domain.repositories.HomeRepo
import com.gendy.bugIt.utils.retrofit.SafeApiCall
import javax.inject.Inject

class HomeRepoImp @Inject constructor(
    private var api: HomeApis
) : HomeRepo, SafeApiCall {


    override suspend fun getBugData() = safeApiCall {
        api.getBugData()
    }


    override suspend fun createASheetTabWithDate(date: String) = safeApiCall {
        api.createSheet(sheetName = date)
    }


}
