package com.mokelab.training.app2025.feature.article

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mokelab.training.app2025.core.data.ArticleRepository
import com.mokelab.training.app2025.core.model.Article
import com.mokelab.training.app2025.core.model.ArticleId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleDetailViewModel @Inject constructor(
    private val articleRepository: ArticleRepository,
    savedStateHandle: SavedStateHandle,
    getArticleId: (SavedStateHandle) -> ArticleId,
) : ViewModel() {
    sealed interface UiState {
        data object Initial : UiState
        data object Loading : UiState
        data class Success(
            val article: Article,
        ) : UiState

        data class Error(
            val message: String,
            val th: Throwable,
        ) : UiState
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Initial)
    val uiState = _uiState.asStateFlow()

    private val articleId = getArticleId(savedStateHandle)

    fun load() {
        _uiState.value = UiState.Loading
        viewModelScope.launch {
            try {
                val article = articleRepository.getById(articleId)
                if (article == null) {
                    _uiState.value = UiState.Error(
                        message = "Article not found",
                        th = IllegalArgumentException("Article not found"),
                    )
                    return@launch
                }
                _uiState.value = UiState.Success(article)
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _uiState.value = UiState.Error(
                    message = e.message ?: e.toString(),
                    th = e,
                )
            }
        }
    }
}