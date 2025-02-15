package com.mokelab.training.app2025.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mokelab.training.app2025.ArticleGraph
import com.mokelab.training.app2025.feature.article.ArticleDetailScreen
import com.mokelab.training.app2025.feature.article.ArticleDetailViewModel
import com.mokelab.training.app2025.feature.article.ArticleListScreen
import com.mokelab.training.app2025.feature.article.ArticleListViewModel
import kotlinx.serialization.Serializable

fun NavGraphBuilder.articleGraph(
    navController: NavController,
) {
    navigation<ArticleGraph>(startDestination = ArticleList) {
        composable<ArticleList> {
            val viewModel: ArticleListViewModel = hiltViewModel()
            ArticleListScreen(
                viewModel = viewModel,
                toDetail = { articleId ->
                    navController.navigate(ArticleDetail(articleId.value))
                },
            )
        }
        composable<ArticleDetail> { backStackEntry ->
            val viewModel: ArticleDetailViewModel = hiltViewModel()
            ArticleDetailScreen(
                viewModel = viewModel,
                back = {
                    navController.popBackStack()
                },
            )
        }
    }
}

@Serializable
data object ArticleList

@Serializable
data class ArticleDetail(val articleId: String)