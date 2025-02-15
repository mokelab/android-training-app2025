package com.mokelab.training.app2025.feature.article

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mokelab.training.app2025.core.design.ProgressIndicator
import com.mokelab.training.app2025.core.design.Screen
import com.mokelab.training.app2025.core.design.theme.TrainingApp2025Theme
import com.mokelab.training.app2025.core.model.Article
import com.mokelab.training.app2025.core.model.ArticleId
import com.mokelab.training.app2025.core.ui.ArticleItem
import com.mokelab.training.app2025.core.ui.LoadError
import java.util.Date

@Composable
fun ArticleListScreen(
    viewModel: ArticleListViewModel,
    toDetail: (ArticleId) -> Unit,
    modifier: Modifier = Modifier,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    ArticleListScreen(
        uiState = uiState,
        load = { viewModel.load() },
        toDetail = toDetail,
        modifier = modifier,
    )
}

@Composable
private fun ArticleListScreen(
    uiState: ArticleListViewModel.UiState,
    load: () -> Unit,
    toDetail: (ArticleId) -> Unit,
    modifier: Modifier,
) {
    Screen(
        modifier = modifier,
    ) { paddingValues ->
        when (uiState) {
            ArticleListViewModel.UiState.Initial,
            ArticleListViewModel.UiState.Loading -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize(),
                ) {
                    ProgressIndicator()
                }
            }

            is ArticleListViewModel.UiState.Success -> {
                ArticleList(
                    articles = uiState.articles,
                    toDetail = toDetail,
                    modifier = Modifier.padding(paddingValues),
                )
            }

            is ArticleListViewModel.UiState.Error -> {
                LoadError(
                    message = uiState.message,
                    onRetry = load,
                    modifier = Modifier.padding(paddingValues),
                )
            }
        }
    }

    LaunchedEffect(uiState) {
        when (uiState) {
            ArticleListViewModel.UiState.Initial -> {
                load()
            }

            ArticleListViewModel.UiState.Loading -> Unit
            is ArticleListViewModel.UiState.Success -> Unit
            is ArticleListViewModel.UiState.Error -> Unit
        }
    }
}

@Composable
private fun ArticleList(
    articles: List<Article>,
    toDetail: (ArticleId) -> Unit,
    modifier: Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        items(articles) { article ->
            ArticleItem(
                article = article,
                modifier = Modifier.clickable {
                    toDetail(article.id)
                }
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    TrainingApp2025Theme {
        ArticleListScreen(
            uiState = ArticleListViewModel.UiState.Success(
                articles = listOf(
                    Article(
                        id = ArticleId("1"),
                        title = "Title",
                        content = "Content",
                        createdAt = Date()
                    ),
                    Article(
                        id = ArticleId("2"),
                        title = "Title2",
                        content = "Content2",
                        createdAt = Date()
                    ),
                )
            ),
            load = {},
            toDetail = {},
            modifier = Modifier,
        )
    }
}