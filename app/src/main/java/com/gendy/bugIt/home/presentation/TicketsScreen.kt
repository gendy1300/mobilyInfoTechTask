package com.gendy.bugIt.home.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.gendy.bugIt.home.domain.model.BugsListModel
import com.gendy.bugIt.home.viewmodel.HomeViewIntent
import com.gendy.bugIt.home.viewmodel.HomeViewmodel
import com.gendy.bugIt.utils.BugItText


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
            bugList.clear()
            bugList.addAll((uiState as TicketsUiState.Success).bugsList)

        }
    }


    TicketsLayout(bugsList = bugList, isRefreshing = refreshing, onRefresh = {

        refreshing = true
        viewmodel.processIntent(HomeViewIntent.GetBugData)

    })
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TicketsLayout(
    bugsList: List<BugsListModel>,
    isRefreshing: Boolean,
    onRefresh: () -> Unit
) {
    val pullToRefreshState = rememberPullRefreshState(isRefreshing, onRefresh)
    if (bugsList.isNotEmpty())
        Box(
            modifier = Modifier
                .pullRefresh(pullToRefreshState)
        ) {
            LazyColumn {
                items(bugsList) { item ->
                    BugItText(text = item.title)
                }
            }


            PullRefreshIndicator(
                isRefreshing,
                pullToRefreshState,
                Modifier.align(Alignment.TopCenter)
            )
        }


}