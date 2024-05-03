package com.gendy.bugIt.home.domain.repositories

import com.gendy.bugIt.home.data.model.OneSheetResponse
import com.gendy.bugIt.home.data.model.SpreadSheetResponseData
import com.gendy.bugIt.utils.retrofit.ApiResult

interface HomeRepo {

    suspend fun getBugData(): ApiResult<SpreadSheetResponseData>
    suspend fun getDataFromSheet(titles:String): ApiResult<OneSheetResponse>


}
