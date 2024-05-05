package com.gendy.bugIt.utils

import android.content.ContentResolver
import android.content.Context
import android.content.ContextWrapper
import android.database.Cursor
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.material3.SnackbarHostState
import androidx.core.net.toFile
import com.gendy.bugIt.home.domain.model.BugsListModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.time.LocalDate
import java.time.format.DateTimeFormatter

internal fun String.appendParams(vararg params: Pair<String, Any?>): String {
    val builder = StringBuilder(this)

    params.forEach {
        it.second?.toString()?.let { arg ->
            builder.append("/$arg")
        }
    }

    return builder.toString()
}


fun showSnackBar(scope: CoroutineScope, snackBarHost: SnackbarHostState, message: String?) {
    scope.launch {
        snackBarHost.showSnackbar(message = message ?: "An Error Has Occurred")
    }
}


fun logDebug(message: String, tag: String = "BugIt") {
    Log.d(tag, message)
}


fun String.checkIfItHasTodaySheet(): Boolean {
    val inputFormat = DateTimeFormatter.ofPattern("yyyy/M/d")
    return try {

        val date = LocalDate.parse(this, inputFormat)
        val today = LocalDate.now()

        date.isEqual(today) || date.isAfter(today)

    } catch (_: Exception) {
        false
    }
}

fun createTodayDate(): String {
    val currentDateTime = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy/M/d")
    return currentDateTime.format(formatter)
}

fun createEmptyBugModel(): BugsListModel {
    return BugsListModel(
        title = "",
        description = "",
        photo = "",
        reporterName = "",
        date = ""
    )
}

fun getImageFile(uri: Uri, context: Context): File? {
    return try {
        val inputStream = context.contentResolver.openInputStream(uri)
        val outputStream: OutputStream
        val file = File(
            context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
            "${getImageName(context.contentResolver, uri)}.jpg"
        )
        outputStream = FileOutputStream(file)
        inputStream?.copyTo(outputStream)
        inputStream?.close()
        outputStream.close()

        file
    } catch (_: Exception) {
        uri.toFile()
    }

}


fun getImageName(contentResolver: ContentResolver, imageUri: Uri): String? {
    var imageName: String? = null
    val projection = arrayOf(MediaStore.Images.Media.DISPLAY_NAME)
    val cursor: Cursor? = contentResolver.query(imageUri, projection, null, null, null)
    cursor?.use {
        if (it.moveToFirst()) {
            val columnIndex: Int =
                it.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
            imageName = it.getString(columnIndex)
        }
    }
    return imageName
}


fun Context.getActivity(): ComponentActivity? = when (this) {
    is ComponentActivity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}