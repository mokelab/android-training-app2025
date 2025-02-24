package com.mokelab.training.app2025.feature.article

import android.content.Context
import com.mokelab.training.app2025.core.data.ArticleRepository
import com.mokelab.training.app2025.core.model.Article
import com.mokelab.training.app2025.core.model.ArticleId
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.util.Date

class ArticleListViewModelTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testLoadSuccess() = runTest {
        val repo = mockk<ArticleRepository>()
        val context = mockk<Context>()
        val viewModel = ArticleListViewModel(
            articleRepository = repo,
            context = context,
        )

        coEvery { repo.load() } returns listOf(
            Article(
                id = ArticleId("id01"),
                title = "title",
                content = "content",
                createdAt = Date(),
            )
        )

        assertThat(
            viewModel.uiState.value,
            instanceOf(ArticleListViewModel.UiState.Initial::class.java)
        )
        viewModel.load()
        advanceUntilIdle()
        assertThat(
            viewModel.uiState.value,
            instanceOf(ArticleListViewModel.UiState.Success::class.java)
        )
        val articles = (viewModel.uiState.value as ArticleListViewModel.UiState.Success)
            .articles
        assertThat(articles.size, `is`(1))

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testLoadErrorSuccess() = runTest {
        val repo = mockk<ArticleRepository>()
        val context = mockk<Context>()
        val viewModel = ArticleListViewModel(
            articleRepository = repo,
            context = context,
        )

        coEvery { repo.load() } throws Exception("Failed to load")

        assertThat(
            viewModel.uiState.value,
            instanceOf(ArticleListViewModel.UiState.Initial::class.java)
        )
        viewModel.load()
        advanceUntilIdle()
        assertThat(
            viewModel.uiState.value,
            instanceOf(ArticleListViewModel.UiState.Error::class.java)
        )
        val exception = (viewModel.uiState.value as ArticleListViewModel.UiState.Error)
            .th
        assertThat(exception, instanceOf(Exception::class.java))

        // retry

        coEvery { repo.load() } returns listOf(
            Article(
                id = ArticleId("id01"),
                title = "title",
                content = "content",
                createdAt = Date(),
            )
        )
        viewModel.load()
        advanceUntilIdle()
        assertThat(
            viewModel.uiState.value,
            instanceOf(ArticleListViewModel.UiState.Success::class.java)
        )
        val articles = (viewModel.uiState.value as ArticleListViewModel.UiState.Success)
            .articles
        assertThat(articles.size, `is`(1))
    }
}