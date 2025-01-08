package com.mokelab.training.app2025.core.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mokelab.training.app2025.core.design.TextListItem
import com.mokelab.training.app2025.core.design.theme.TrainingApp2025Theme
import com.mokelab.training.app2025.core.model.Article
import com.mokelab.training.app2025.core.model.ArticleId
import java.util.Date

@Composable
fun ArticleItem(
    article: Article,
    modifier: Modifier = Modifier,
) {
    TextListItem(
        headline = article.title,
        trailing = article.createdAt.format(),
        modifier = modifier,
    )
}

@Preview
@Composable
private fun PreviewArticleItem() {
    TrainingApp2025Theme {
        Column {
            ArticleItem(
                article = Article(
                    id = ArticleId("1"),
                    title = "Title",
                    content = "Content",
                    createdAt = Date()
                )
            )
            ArticleItem(
                article = Article(
                    id = ArticleId("2"),
                    title = "Title2",
                    content = "Content2",
                    createdAt = Date()
                )
            )
        }
    }
}