package com.mokelab.training.app2025.core.model

import java.util.Date

@JvmInline
value class CommentId(val value: String)

/**
 * コメントを表すクラス
 */
data class Comment(
    val articleId: ArticleId,
    val commentId: CommentId,
    val user: User,
    val content: String,
    val createdAt: Date,
)
