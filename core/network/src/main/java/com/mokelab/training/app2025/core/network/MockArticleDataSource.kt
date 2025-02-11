package com.mokelab.training.app2025.core.network

import com.mokelab.training.app2025.core.network.model.NetworkArticle
import javax.inject.Inject

class MockArticleDataSource @Inject constructor() : NetworkArticleDataSource {
    override suspend fun fetch(startTimeMillis: Long): List<NetworkArticle> {
        return listOf(
            NetworkArticle(
                id = "1",
                title = "Title 1",
                content = "Content 1",
                createdAtMillis = 1736730000000L,
            ),
            NetworkArticle(
                id = "2",
                title = "Title 2",
                content = "Content 2",
                createdAtMillis = 1736211600000L,
            ),
        )
    }

    override suspend fun fetchById(id: String): NetworkArticle? {
        return NetworkArticle(
            id = id,
            title = "Title $id",
            content = "Content $id",
            createdAtMillis = 1736730000000L,
        )
    }
}