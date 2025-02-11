package com.mokelab.training.app2025.core.data

import com.mokelab.training.app2025.core.data.model.toArticle
import com.mokelab.training.app2025.core.model.Article
import com.mokelab.training.app2025.core.model.ArticleId
import com.mokelab.training.app2025.core.network.NetworkArticleDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OnlineArticleRepository @Inject constructor(
    private val networkDataSource: NetworkArticleDataSource,
) : ArticleRepository {
    override suspend fun load(): List<Article> {
        return networkDataSource.fetch(System.currentTimeMillis())
            .map { it.toArticle() }
    }

    override suspend fun getById(id: ArticleId): Article? {
        TODO("Not yet implemented")
    }
}
