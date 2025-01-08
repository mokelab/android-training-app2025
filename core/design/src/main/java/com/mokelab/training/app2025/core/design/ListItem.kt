package com.mokelab.training.app2025.core.design

import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TextListItem(
    headline: String,
    trailing: String,
    modifier: Modifier = Modifier,
) {
    ListItem(
        headlineContent = { MediumText(headline) },
        trailingContent = { SmallText(trailing) },
        modifier = modifier,
    )
    HorizontalDivider()
}