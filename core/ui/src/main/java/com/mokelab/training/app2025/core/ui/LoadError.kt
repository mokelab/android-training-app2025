package com.mokelab.training.app2025.core.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mokelab.training.app2025.core.design.ElevatedButton
import com.mokelab.training.app2025.core.design.MediumText
import com.mokelab.training.app2025.core.design.Screen

@Composable
fun LoadError(
    message: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize(),
    ) {
        MediumText(message)
        ElevatedButton(stringResource(R.string.retry), onClick = onRetry)
    }
}

@Preview
@Composable
private fun PreviewLoadError() {
    Screen { innerPadding ->
        LoadError(
            message = "Failed to load",
            onRetry = {},
            modifier = Modifier.padding(innerPadding),
        )
    }
}
