package com.mokelab.training.app2025.core.data

import com.mokelab.training.app2025.core.model.Article
import com.mokelab.training.app2025.core.model.ArticleId

interface ArticleRepository {
    suspend fun load(): List<Article>
    suspend fun getById(id: ArticleId): Article?
}