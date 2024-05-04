package com.gendy.bugIt.addBug.data.repository

import com.gendy.bugIt.addBug.data.model.AddBugRequest
import com.gendy.bugIt.addBug.data.model.AddSheet
import com.gendy.bugIt.addBug.data.model.AddSheetRequest
import com.gendy.bugIt.addBug.data.model.SheetProperties
import com.gendy.bugIt.addBug.data.remote.AddBugApis
import com.gendy.bugIt.addBug.domain.repositories.AddBugRepo
import com.gendy.bugIt.utils.retrofit.SafeApiCall
import javax.inject.Inject

class AddABugRepoImp @Inject constructor(
    private var api: AddBugApis
) : AddBugRepo, SafeApiCall {


}