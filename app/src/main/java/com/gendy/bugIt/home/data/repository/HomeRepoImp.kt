package com.gendy.bugIt.home.data.repository

import com.gendy.bugIt.home.data.remote.HomeApis
import com.gendy.bugIt.home.domain.repositories.HomeRepo
import com.gendy.bugIt.utils.retrofit.SafeApiCall
import javax.inject.Inject

class HomeRepoImp @Inject constructor(
    private var api: HomeApis
) : HomeRepo, SafeApiCall {


    override suspend fun getBugData() = safeApiCall {
        api.getBugData("1d4e_o0qAMeUpdjMTIacBEVc7eRJbBc_MTjks2Q3snfU")
    }


}
