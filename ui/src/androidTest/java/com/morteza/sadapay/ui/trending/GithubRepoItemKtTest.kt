package com.morteza.sadapay.ui.trending

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.morteza.sadapay.presentation.model.GithubRepoOwnerPresentationModel
import com.morteza.sadapay.presentation.model.GithubRepoPresentationModel
import org.junit.Rule
import org.junit.Test

class GithubRepoItemKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun githubRepoItemExpandedState() {
        val repository = GithubRepoPresentationModel(
            id = 11,
            name = "SadaPay",
            owner = GithubRepoOwnerPresentationModel(
                22, "Morteza Khosravinejad", "",
            ),
            fullName = "SadaPay Take-Home Exercise",
            description = "This is a sample repository",
            language = "Kotlin",
            starsCount = 100
        )

        composeTestRule.setContent {
            GithubRepoItem(repository = repository)
        }

        val repoItem = composeTestRule.onNodeWithTag("repoItem")
        val expandedContent =
            composeTestRule.onNodeWithTag("expandedContent", useUnmergedTree = true)

        // Assert that initially, the expanded content is not visible
        expandedContent.assertDoesNotExist()

        // Click on the repo item to expand it
        repoItem.performClick()

        // Assert that after clicking, the expanded content becomes visible
        expandedContent.assertExists()
    }
}