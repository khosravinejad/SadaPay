package com.morteza.sadapay.ui.trending

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.fade
import com.google.accompanist.placeholder.material.placeholder
import com.morteza.sadapay.ui.theme.Gray


// TODO Refactoring needed
/*
*
* It's not the best solution, I used it due to time limitation.
* The better solution is to using placeholder by its main purpose
*
* */

@Composable
fun TrendingLoading() {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(10) {
            ShimmerGithubRepoItem()
        }
    }
}

@Composable
fun ShimmerGithubRepoItem() {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 8.dp, start = 16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .placeholder(
                        visible = true,
                        highlight = PlaceholderHighlight.fade()
                    )
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 8.dp, end = 8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .height(24.dp)
                        .fillMaxWidth(0.4f)
                        .padding(bottom = 8.dp)
                        .placeholder(
                            visible = true,
                            highlight = PlaceholderHighlight.fade()
                        )
                )
                Box(
                    modifier = Modifier
                        .height(16.dp)
                        .fillMaxWidth(0.6f)
                        .placeholder(
                            visible = true,
                            highlight = PlaceholderHighlight.fade()
                        )
                )
            }
        }
        Divider(
            color = Gray,
            thickness = 1.dp,
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp)
                .placeholder(
                    visible = true,
                    highlight = PlaceholderHighlight.fade()
                )
        )
    }
}