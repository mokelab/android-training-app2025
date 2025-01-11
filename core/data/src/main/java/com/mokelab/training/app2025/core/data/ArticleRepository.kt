package com.mokelab.training.app2025.core.data

import com.mokelab.training.app2025.core.model.Article

interface ArticleRepository {
    suspend fun load(): List<Article>
}