package com.nhatbui.common.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun HeaderBar(
    modifier: Modifier = Modifier,
    title: String = "",
    titleColor: Color = Color.White,
    backButton: @Composable (action: () -> Unit) -> Unit = { action -> DefaultBackButton(action) },
    actionButton: @Composable () -> Unit = { DefaultEmptyButton() },
    onNavigateBack: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        backButton(onNavigateBack)
        Text(
            modifier = Modifier.weight(1f),
            text = title,
            textAlign = TextAlign.Center,
            color = titleColor
        )
        actionButton()
    }
}

@Composable
private fun DefaultBackButton(
    onNavigateBack: () -> Unit
) {
    IconButton(onClick = onNavigateBack) {
        Icon(
            modifier = Modifier.size(24.dp),
            painter = painterResource(R.drawable.ic_arrow_left),
            tint = Color.Unspecified,
            contentDescription = stringResource(R.string.back_label)
        )
    }
}

@Composable
private fun DefaultEmptyButton() {
    Box(modifier = Modifier.size(24.dp))
}
