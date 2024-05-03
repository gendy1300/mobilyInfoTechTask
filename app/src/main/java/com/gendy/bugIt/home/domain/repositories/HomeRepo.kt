package com.gendy.bugIt.home.domain.repositories

import com.gendy.bugIt.utils.retrofit.ApiResult

interface HomeRepo {

    suspend fun getBugData(): ApiResult<String>


}
