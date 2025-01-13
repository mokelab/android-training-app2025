package com.mokelab.training.app2025.core.data.model

import com.mokelab.training.app2025.core.model.Article
import com.mokelab.training.app2025.core.model.ArticleId
import com.mokelab.training.app2025.core.network.model.NetworkArticle
import java.util.Date

internal fun NetworkArticle.toArticle(): Article {
    return Article(
        id = ArticleId(id),
        title = title,
        content = content,
        createdAt = Date(createdAtMillis),
    )
}