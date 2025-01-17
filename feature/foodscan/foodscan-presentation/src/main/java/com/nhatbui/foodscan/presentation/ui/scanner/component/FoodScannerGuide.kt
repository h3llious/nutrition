package com.nhatbui.foodscan.presentation.ui.scanner.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nhatbui.foodscan.presentation.R

@Composable
fun FoodScanGuide(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .background(Color(0x33FFFFFF), RoundedCornerShape(12.dp))
            .padding(8.dp),
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            modifier = Modifier.size(32.dp),
            painter = painterResource(R.drawable.ic_scan_border),
            tint = Color.White,
            contentDescription = null
        )
        Spacer(Modifier.width(12.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = stringResource(R.string.food_scan_guide_title),
                style = MaterialTheme.typography.titleSmall,
                color = Color.White
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = stringResource(R.string.food_scan_guide_description),
                color = Color.White,
                fontWeight = FontWeight.W400,
                fontSize = 10.sp,
                lineHeight = 14.sp
            )
        }
    }
}