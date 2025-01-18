package com.nhatbui.foodscan.presentation.ui.result.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ResultCard(
    @DrawableRes iconRes: Int,
    title: String,
    description: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFFF6F6F6),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(vertical = 8.dp, horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            modifier = Modifier.size(32.dp),
            painter = painterResource(iconRes),
            tint = Color.Unspecified,
            contentDescription = null
        )
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(text = title, style = MaterialTheme.typography.labelMedium)
            Text(
                text = description,
                fontWeight = FontWeight.W500,
                fontSize = 14.sp,
                lineHeight = 18.sp
            )
        }
    }
}
