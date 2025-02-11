package com.mokelab.training.app2025.feature.article

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mokelab.training.app2025.core.design.AppBar
import com.mokelab.training.app2025.core.design.LargeText
import com.mokelab.training.app2025.core.design.MediumText
import com.mokelab.training.app2025.core.design.ProgressIndicator
import com.mokelab.training.app2025.core.design.Screen
import com.mokelab.training.app2025.core.design.theme.TrainingApp2025Theme
import com.mokelab.training.app2025.core.model.Article
import com.mokelab.training.app2025.core.model.ArticleId
import com.mokelab.training.app2025.core.ui.LoadError
import com.mokelab.training.app2025.core.ui.SmallDateText
import java.util.Date

@Composable
fun ArticleDetailScreen(
    viewModel: ArticleDetailViewModel,
    back: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    ArticleDetailScreen(
        uiState = uiState,
        back = back,
        load = {
            viewModel.load()
        },
        modifier = modifier,
    )
}

@Composable
private fun ArticleDetailScreen(
    uiState: ArticleDetailViewModel.UiState,
    back: () -> Unit,
    load: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Screen(
        topBar = {
            AppBar(
                title = when (uiState) {
                    ArticleDetailViewModel.UiState.Initial,
                    ArticleDetailViewModel.UiState.Loading -> stringResource(R.string.loading)

                    is ArticleDetailViewModel.UiState.Success -> uiState.article.title
                    is ArticleDetailViewModel.UiState.Error -> stringResource(R.string.load_error)
                },
                back = back,
            )
        },
        modifier = modifier,
    ) { paddingValues ->
        when (uiState) {
            ArticleDetailViewModel.UiState.Initial,
            ArticleDetailViewModel.UiState.Loading -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize(),
                ) {
                    ProgressIndicator()
                }
            }

            is ArticleDetailViewModel.UiState.Success -> {
                ArticleDetail(
                    article = uiState.article,
                    modifier = Modifier.padding(paddingValues),
                )
            }

            is ArticleDetailViewModel.UiState.Error -> {
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
            ArticleDetailViewModel.UiState.Initial -> {
                load()
            }

            ArticleDetailViewModel.UiState.Loading -> Unit
            is ArticleDetailViewModel.UiState.Success -> Unit
            is ArticleDetailViewModel.UiState.Error -> Unit
        }
    }
}

@Composable
fun ArticleDetail(
    article: Article,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        LargeText(
            text = article.title,
            modifier = Modifier.padding(all = 16.dp),
        )
        SmallDateText(
            date = article.createdAt,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        MediumText(
            text = article.content,
            modifier = Modifier.padding(all = 16.dp),
        )
    }
}

@Preview
@Composable
private fun PreviewArticleDetailScreen() {
    TrainingApp2025Theme {
        ArticleDetailScreen(
            uiState = ArticleDetailViewModel.UiState.Success(
                Article(
                    id = ArticleId("1"),
                    title = "Title",
                    content = "Content\nContent\nContent",
                    createdAt = Date(),
                )
            ),
            back = {},
            load = {},
        )
    }
}