package com.mokelab.training.app2025.core.data

import com.mokelab.training.app2025.core.model.Article
import com.mokelab.training.app2025.core.model.ArticleId
import kotlinx.coroutines.delay
import java.util.Calendar
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OnlineArticleRepository @Inject constructor() : ArticleRepository {
    override suspend fun load(): List<Article> {
        delay(2000)
        return listOf(
            Article(
                id = ArticleId("1"),
                title = "Title 1",
                content = "Content 1",
                createdAt = fromYearMonthDay(2025, 1, 3),
            ),
            Article(
                id = ArticleId("2"),
                title = "Title 2",
                content = "Content 2",
                createdAt = fromYearMonthDay(2024, 12, 31),
            ),
        )
    }
}

internal fun fromYearMonthDay(
    year: Int,
    month: Int,
    day: Int
): Date {
    return Calendar.getInstance().apply {
        set(Calendar.YEAR, year)
        set(Calendar.MONTH, month - 1)
        set(Calendar.DAY_OF_MONTH, day)
    }.time
}
