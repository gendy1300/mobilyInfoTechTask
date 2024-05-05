package com.gendy.bugIt.addBug.viewmodel

import android.content.Context
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.lifecycle.LifecycleOwner

sealed class AddBugViewIntent {

    data class GetImage(
        val lifecycleOwner: LifecycleOwner,
        val context: Context,
        val currentActivity: ComponentActivity
    ) : AddBugViewIntent()
    data object UploadBug : AddBugViewIntent()
}