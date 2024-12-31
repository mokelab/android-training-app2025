package com.mokelab.training.app2025.core.model

@JvmInline
value class UserId(val value: String)

/**
 * ユーザーを表すクラス
 */
data class User(
    val id: UserId,
    val name: String,
)
