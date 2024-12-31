package com.mokelab.training.app2025.core.design

import androidx.compose.material3.Button
import androidx.compose.runtime.Composable

@Composable
fun ElevatedButton(
    text: String,
    onClick: () -> Unit,
) {
    Button(onClick = onClick) {
        MediumText(text = text)
    }
}

@Composable
fun TextButton(
    text: String,
    onClick: () -> Unit,
) {
    androidx.compose.material3.TextButton(onClick = onClick) {
        MediumText(text = text)
    }
}

