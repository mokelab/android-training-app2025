package com.mokelab.training.app2025.core.ui

import android.icu.text.DateFormat
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mokelab.training.app2025.core.design.SmallText
import java.util.Date

@Composable
fun SmallDateText(
    date: Date,
    modifier: Modifier = Modifier,
) {
    SmallText(
        text = date.format(),
        modifier = modifier,
    )
}

internal fun Date.format(): String {
    return DateFormat.getDateInstance().format(this)
}