package com.morteza.sadapay.ui.trending

import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.morteza.sadapay.presentation.model.GithubRepoPresentationModel
import com.morteza.sadapay.ui.R
import com.morteza.sadapay.ui.theme.Gray

@Composable
fun GithubRepoItem(repository: GithubRepoPresentationModel) {
    val imagePainter = rememberAsyncImagePainter(
        model = repository.owner.avatarUrl,
    )
    var isExpanded by remember { mutableStateOf(false) }
    val alpha by animateFloatAsState(if (isExpanded) 1f else 0f)

    Column(
        modifier = Modifier
            .clickable {
                isExpanded = !isExpanded
            }
            .testTag("repoItem")
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 8.dp, start = 16.dp)
        ) {
            Image(
                painter = imagePainter,
                contentDescription = "Owner Avatar",
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(start = 8.dp, end = 8.dp)) {
                Text(
                    text = repository.owner.name,
                    style = MaterialTheme.typography.subtitle1
                )
                Text(
                    text = repository.fullName,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.secondary
                )

                AnimatedVisibility(
                    visible = isExpanded,
                    enter = fadeIn() + expandVertically(expandFrom = Alignment.Top),
                    exit = fadeOut() + shrinkVertically(shrinkTowards = Alignment.Top)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .alpha(alpha)
                            .testTag("expandedContent")
                    ) {
                        Text(
                            text = repository.description,
                            style = MaterialTheme.typography.body2,
                            maxLines = 5,
                            overflow = TextOverflow.Ellipsis,
                        )
                        Row(
                            modifier = Modifier.padding(top = 8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(10.dp)
                                    .clip(CircleShape)
                                    .background(Color.Green)
                                    .align(Alignment.CenterVertically)
                            )
                            Text(
                                text = repository.language,
                                style = MaterialTheme.typography.body2,
                                modifier = Modifier
                                    .padding(start = 4.dp, end = 12.dp)
                                    .align(Alignment.CenterVertically)
                            )
                            Image(
                                painter = painterResource(id = R.drawable.star_rate),
                                contentDescription = "SVG Icon",
                                modifier = Modifier
                                    .size(18.dp)
                                    .align(Alignment.CenterVertically)
                            )
                            Text(
                                text = repository.starsCount.toString(),
                                style = MaterialTheme.typography.body2,
                                modifier = Modifier
                                    .padding(start = 4.dp)
                                    .align(Alignment.CenterVertically)
                            )
                        }
                    }
                }
            }
        }
        Divider(
            color = Gray,
            thickness = 1.dp,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp)
        )
    }
}