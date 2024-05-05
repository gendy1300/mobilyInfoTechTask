package com.gendy.bugIt.addBug.viewmodel

import android.net.Uri

sealed class AddBugViewIntent {


    data object UploadBug : AddBugViewIntent()
}