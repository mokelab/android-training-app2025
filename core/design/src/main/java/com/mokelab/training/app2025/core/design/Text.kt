package com.mokelab.training.app2025.core.design

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun MediumText(
    text: String,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
    )
}

@Composable
fun SmallText(
    text: String,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodySmall,
    )
}
