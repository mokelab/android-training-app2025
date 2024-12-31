package com.mokelab.training.app2025.core.model

import java.util.Date

@JvmInline
value class NewsId(val value: String)

/**
 * お知らせを表すクラス
 */
data class News(
    val id: NewsId,
    val title: String,
    val content: String,
    val createdAt: Date,
    val isRead: Boolean,
)