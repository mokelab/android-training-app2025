package com.mokelab.training.app2025.core.network

import com.mokelab.training.app2025.core.network.model.NetworkArticle
import com.mokelab.training.app2025.core.network.model.NetworkException
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.Url
import kotlinx.io.IOException
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.nio.channels.UnresolvedAddressException
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class HttpArticleDataSource @Inject constructor(
    private val client: HttpClient,
    @Named("baseUrl") private val baseUrl: String,
) : NetworkArticleDataSource {
    override suspend fun fetch(startTimeMillis: Long): List<NetworkArticle> {
        return try {
            val resp = client.get(Url("${baseUrl}/getArticle?t=${startTimeMillis}"))
            if (resp.status.value != 200) {
                throw NetworkException(
                    status = resp.status.value,
                    body = resp.body(),
                )
            }
            val respBody: FetchResponse = Json.decodeFromString(resp.body())
            respBody.articles
        } catch (e: UnresolvedAddressException) {
            throw IOException(e)
        }
    }
}

@Serializable
private class FetchResponse(
    val articles: List<NetworkArticle>
)