package com.mokelab.training.app2025.feature.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mokelab.training.app2025.core.data.ArticleRepository
import com.mokelab.training.app2025.core.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleListViewModel @Inject constructor(
    private val articleRepository: ArticleRepository,
) : ViewModel() {

    sealed interface UiState {
        data object Initial : UiState
        data object Loading : UiState
        data class Success(val articles: List<Article>) : UiState
        data class Error(val th: Throwable) : UiState
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Initial)
    val uiState = _uiState.asStateFlow()

    fun load() {
        _uiState.value = UiState.Loading
        viewModelScope.launch {
            try {
                val articles = articleRepository.load()
                _uiState.value = UiState.Success(articles = articles)
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e)
            }
        }
    }
}