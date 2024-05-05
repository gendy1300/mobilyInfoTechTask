package com.gendy.bugIt.addBug.presentation

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.gendy.bugIt.R
import com.gendy.bugIt.addBug.domain.model.AddBugScreenFields
import com.gendy.bugIt.addBug.viewmodel.AddBugViewIntent
import com.gendy.bugIt.addBug.viewmodel.AddBugViewmodel
import com.gendy.bugIt.utils.AppHeader
import com.gendy.bugIt.utils.BugItButton
import com.gendy.bugIt.utils.BugItTextField
import com.gendy.bugIt.utils.LabelText
import com.gendy.bugIt.utils.Margin
import com.gendy.bugIt.utils.getImageFile
import com.gendy.bugIt.utils.theme.ScreenPadding


@Composable
fun AddBugScreen(
    viewmodel: AddBugViewmodel = hiltViewModel(),
    showSnackBar: (errorMessage: String?) -> Unit,
    showLoading: (Boolean) -> Unit
) {

    val addBugScreenFields by viewmodel.addBugFields



    AddBugLayout(addBugScreenFields) { imageUri: Uri ->
        viewmodel.processIntent(AddBugViewIntent.UploadBug)
    }

}


@Composable
fun AddBugLayout(addBugScreenFields: AddBugScreenFields, onConfirm: (imageUri: Uri) -> Unit) {

    val context = LocalContext.current

    var imageFile by remember(addBugScreenFields) {
        mutableStateOf(addBugScreenFields.photoFile)
    }

    var title by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }


    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        AppHeader(title = stringResource(id = R.string.addABug))
    }) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(ScreenPadding)
                .verticalScroll(state = rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(10.dp),

            ) {

            Margin(10.dp)
            AsyncImage(
                model = imageFile,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .border(width = 3.dp, color = Color.Gray)
            )



            AddBugImage(modifier = Modifier.align(Alignment.CenterHorizontally)) { imageUri: Uri ->
                imageFile = getImageFile(imageUri, context)
            }

            BugItTextField(modifier = Modifier.fillMaxWidth(), value = title, onValueChange = {
                title = it
            }, label = { LabelText(text = stringResource(id = R.string.title)) })

            BugItTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = (5 * 24).dp),
                value = description,
                minLines = 5,
                singleLine = false,
                onValueChange = {
                    description = it
                },
                label = {
                    LabelText(text = stringResource(id = R.string.title))
                })

            BugItButton(
                text = stringResource(id = R.string.addABug),
                modifier = Modifier.fillMaxWidth(0.7f)
            ) {

            }

        }

    }

}

@Composable
fun AddBugImage(modifier: Modifier = Modifier, onImageSelected: (Uri) -> Unit) {
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                onImageSelected(uri)
            }
        }

    BugItButton(
        modifier = modifier,
        text = stringResource(id = R.string.chooseAnImage),
        onClick = { launcher.launch(PickVisualMediaRequest(mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly)) })
}

