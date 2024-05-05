package com.gendy.bugIt.profile.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gendy.bugIt.R
import com.gendy.bugIt.profile.viewmodel.ProfileViewIntent
import com.gendy.bugIt.profile.viewmodel.ProfileViewmodel
import com.gendy.bugIt.utils.AppHeader
import com.gendy.bugIt.utils.BugItButton
import com.gendy.bugIt.utils.BugItText
import com.gendy.bugIt.utils.BugItTextField
import com.gendy.bugIt.utils.theme.ScreenPadding

@Composable
fun ProfileScreen(viewmodel: ProfileViewmodel = hiltViewModel(), showLoading: (Boolean) -> Unit) {

    val reporterName by viewmodel.reporterName

    LaunchedEffect(key1 = Unit) {
        showLoading(false)
    }

    ProfileScreenLayout(reporterName = reporterName) {
        viewmodel.processIntent(ProfileViewIntent.SaveReporterName(it))
    }
}


@Composable
fun ProfileScreenLayout(reporterName: String, onConfirm: (reporterName: String) -> Unit) {

    var reporterNameState: String by remember(reporterName) {
        mutableStateOf(reporterName)
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding()
            .background(color = Color.White),
        topBar = {
            AppHeader(title = stringResource(id = R.string.profile))
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(top = 20.dp)
                .padding(ScreenPadding)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            BugItText(text = stringResource(id = R.string.reportedName))

            BugItTextField(
                modifier = Modifier.fillMaxWidth(),
                value = reporterNameState,
                onValueChange = {
                    reporterNameState = it
                }
            )


            BugItButton(text = stringResource(id = R.string.confirm)) {
                onConfirm(reporterNameState)
            }

        }


    }
}