package com.morteza.sadapay.ui.trending

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.morteza.sadapay.ui.R

@Composable
fun RetryScreen(errorMessage: String, onRetryClicked: () -> Unit) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.retry_animation))
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        LottieAnimation(
            composition = composition,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 64.dp)
                .wrapContentHeight()
                .align(Alignment.CenterHorizontally),
            iterations = LottieConstants.IterateForever
        )
        Text(
            text = "Something went wrong...",
            style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier
                .padding(top = 16.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = errorMessage,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f),
            modifier = Modifier
                .padding(top = 8.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = onRetryClicked,
            modifier = Modifier
                .fillMaxWidth()
                .height(36.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White,
                contentColor = Color.Green,
                disabledBackgroundColor = Color.White.copy(alpha = 0.5f),
                disabledContentColor = Color.Green.copy(alpha = 0.5f)
            ),
            border = BorderStroke(1.dp, Color.Green),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "Retry")
        }
    }
}
