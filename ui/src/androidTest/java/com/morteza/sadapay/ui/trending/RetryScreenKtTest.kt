package com.morteza.sadapay.ui.trending

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class RetryScreenKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testRetryScreen() {
        val errorMessage = "An error occurred"
        var retryButtonClicked = false

        composeTestRule.setContent {
            RetryScreen(errorMessage = errorMessage, onRetryClicked = { retryButtonClicked = true })
        }

        // Assert the content of the RetryScreen composable
        composeTestRule.onNodeWithText("Something went wrong...")
            .assertExists()
        composeTestRule.onNodeWithText(errorMessage)
            .assertExists()

        // Assert the retry button
        composeTestRule.onNodeWithText("Retry")
            .assertExists()
            .performClick()

        // Verify that the onRetryClicked callback was called
        assert(retryButtonClicked)
    }
}