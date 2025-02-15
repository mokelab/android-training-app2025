package com.mokelab.training.app2025

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.mokelab.training.app2025.navigation.articleGraph
import kotlinx.serialization.Serializable

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ArticleGraph) {
        articleGraph(navController = navController)
    }
}

@Serializable
data object ArticleGraph