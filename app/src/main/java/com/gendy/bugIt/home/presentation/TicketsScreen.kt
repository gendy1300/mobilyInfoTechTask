package com.gendy.bugIt.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.gendy.bugIt.R
import com.gendy.bugIt.home.domain.model.BugsListModel
import com.gendy.bugIt.home.viewmodel.HomeViewIntent
import com.gendy.bugIt.home.viewmodel.HomeViewmodel
import com.gendy.bugIt.utils.AppHeader
import com.gendy.bugIt.utils.BugItText
import com.gendy.bugIt.utils.Margin
import com.gendy.bugIt.utils.theme.BlueColor
import com.gendy.bugIt.utils.theme.cardPadding


@Composable
fun TicketsScreen(
    viewmodel: HomeViewmodel = hiltViewModel(),
    showSnackBar: (errorMessage: String?) -> Unit,
    showLoading: (Boolean) -> Unit
) {

    val uiState by viewmodel.ticketsUiState.collectAsState()
    var refreshing by remember { mutableStateOf(false) }

    val bugList = remember {
        mutableStateListOf<BugsListModel>()
    }

    when (uiState) {
        TicketsUiState.Loading -> {
            showLoading(true)
        }

        is TicketsUiState.NetworkError -> {}
        is TicketsUiState.Success -> {
            showLoading(false)
            refreshing = false
            bugList.clear()
            bugList.addAll((uiState as TicketsUiState.Success).bugsList)

        }
    }


    TicketsLayout(bugsList = bugList, isRefreshing = refreshing, onRefresh = {

        refreshing = true
        viewmodel.processIntent(HomeViewIntent.GetBugData)

    }, onItemClicked = {

    })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TicketsLayout(
    bugsList: List<BugsListModel>,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    onItemClicked: () -> Unit
) {
    val pullToRefreshState = rememberPullRefreshState(isRefreshing, onRefresh)
    if (bugsList.isNotEmpty())
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .padding()
                .background(color = Color.White),
            topBar = {
                AppHeader(title = stringResource(id = R.string.tickets))
            }
        ) { padding ->
            Box(
                modifier = Modifier
                    .pullRefresh(pullToRefreshState)
                    .padding(padding)
            ) {
                LazyColumn(
                    modifier = Modifier.padding(top = 80.dp, bottom = 50.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(bugsList) { item ->
                        TicketItemLayout(item)
                    }
                }


                PullRefreshIndicator(
                    isRefreshing,
                    pullToRefreshState,
                    Modifier.align(Alignment.TopCenter)
                )
            }
        }
}


@Composable
fun TicketItemLayout(bugsListModel: BugsListModel) {

    ElevatedCard(
        elevation = CardDefaults.cardElevation(10.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 150.dp)
                .padding(cardPadding)
        ) {

            Column {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {


                    BugItText(
                        text = bugsListModel.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )


                    BugItText(
                        text = bugsListModel.date,
                        fontSize = 9.sp
                    )
                }

                Margin(8.dp)

                BugItText(text = bugsListModel.description, fontSize = 12.sp, maxLines = 3)


                Margin(8.dp)


            }

            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BugItText(
                    text = stringResource(id = R.string.by, bugsListModel.reporterName),
                    fontSize = 12.sp,
                    color = BlueColor
                )

                AsyncImage(
                    model = bugsListModel.photo,
                    contentDescription = null,
                    modifier = Modifier.size(30.dp),
                    contentScale = ContentScale.FillBounds
                )

            }

        }

    }

}