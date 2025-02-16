package com.mokelab.training.app2025

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.mokelab.training.app2025.core.data.ArticleRepository
import com.mokelab.training.app2025.core.data.OnlineArticleRepository
import com.mokelab.training.app2025.core.model.ArticleId
import com.mokelab.training.app2025.core.network.HttpArticleDataSource
import com.mokelab.training.app2025.core.network.NetworkArticleDataSource
import com.mokelab.training.app2025.feature.article.ArticleDetailViewModel
import com.mokelab.training.app2025.navigation.ArticleDetail
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MainModule {
    @Binds
    @Singleton
    abstract fun bindArticleRepository(impl: OnlineArticleRepository): ArticleRepository

    @Binds
    @Singleton
    abstract fun bindNetworkArticleDataSource(impl: HttpArticleDataSource): NetworkArticleDataSource
}

@Module
@InstallIn(SingletonComponent::class)
object ClientModule {
    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(CIO)
    }

    @Provides
    @Named("baseUrl")
    @Singleton
    fun provideBaseUrl(): String {
        return "https://us-central1-trialapp2025-funsite.cloudfunctions.net"
    }
}

@Module
@InstallIn(SingletonComponent::class)
object ArticleParamsModule {
    @Provides
    @Singleton
    fun provideArticleDetailParamsParser(): ArticleDetailViewModel.ArticleDetailParamParser {
        return object : ArticleDetailViewModel.ArticleDetailParamParser {
            override fun parse(handle: SavedStateHandle): ArticleId {
                val route = handle.toRoute<ArticleDetail>()
                return ArticleId(route.articleId)
            }
        }
    }
}