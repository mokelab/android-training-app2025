package com.mokelab.training.app2025.feature.article

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mokelab.training.app2025.core.design.ProgressIndicator
import com.mokelab.training.app2025.core.design.Screen
import com.mokelab.training.app2025.core.design.theme.TrainingApp2025Theme

@Composable
fun ArticleListScreen(
    viewModel: ArticleListViewModel,
    modifier: Modifier = Modifier,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    ArticleListScreen(
        uiState = uiState,
        modifier = modifier,
    )
}

@Composable
private fun ArticleListScreen(
    uiState: ArticleListViewModel.UiState,
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
        }
    }
}

@Preview
@Composable
private fun Preview() {
    TrainingApp2025Theme {
        ArticleListScreen(
            uiState = ArticleListViewModel.UiState.Initial,
            modifier = Modifier,
        )
    }
}