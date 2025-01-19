package com.mokelab.training.app2025.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkArticle(
    val id: String,
    val title: String,
    val content: String,
    @SerialName("created") val createdAtMillis: Long,
)
