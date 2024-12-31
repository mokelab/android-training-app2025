package com.mokelab.training.app2025.core.model

import java.util.Date

@JvmInline
value class ArticleId(val value: String)

/**
 * 記事を表すクラス
 */
data class Article(
    val id: ArticleId,
    val title: String,
    val content: String,
    val createdAt: Date,
)
