package com.gendy.bugIt.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.gendy.bugIt.R
import com.gendy.bugIt.home.domain.model.BugsListModel
import com.gendy.bugIt.home.viewmodel.HomeViewmodel
import com.gendy.bugIt.utils.AppHeader
import com.gendy.bugIt.utils.BugItText
import com.gendy.bugIt.utils.Margin
import com.gendy.bugIt.utils.createEmptyBugModel
import com.gendy.bugIt.utils.theme.ScreenPadding
import com.gendy.bugIt.utils.toFormattedDate

@Composable
fun TicketDetailsScreen(
    viewmodel: HomeViewmodel = hiltViewModel()
) {

    TicketDetailsScreenLayout(viewmodel.selectedBugData.value)

}


@Composable
fun TicketDetailsScreenLayout(bugData: BugsListModel) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding()
            .background(color = Color.White),
        topBar = {
            AppHeader(title = stringResource(id = R.string.tickets))
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
                .padding(ScreenPadding)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            AsyncImage(
                model = bugData.photo,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )


            BugItText(
                text = bugData.title, fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )


            BugItText(text = bugData.description, fontSize = 15.sp, fontWeight = FontWeight.Medium)



            BugItText(text = stringResource(id = R.string.reportedBy, bugData.reporterName))



            BugItText(text = bugData.date.toFormattedDate())


        }

    }
}


@Preview
@Composable
private fun TicketDetailsScreenLayoutPreiver() {
    TicketDetailsScreenLayout(createEmptyBugModel())
}
