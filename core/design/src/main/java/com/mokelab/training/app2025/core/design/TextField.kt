package com.mokelab.training.app2025.core.design

import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable

@Composable
fun TextField(
    value: String,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
    )
}

@Composable
fun TextArea(
    value: String,
    onValueChange: (String) -> Unit,
    minLines: Int,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        minLines = minLines,
    )
}