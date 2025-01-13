package com.mokelab.training.app2025.core.network

import com.mokelab.training.app2025.core.network.model.NetworkArticle

interface NetworkArticleDataSource {
    suspend fun fetch(startTimeMillis: Long): List<NetworkArticle>
}