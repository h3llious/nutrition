package com.nhatbui.nutrition.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nhatbui.nutrition.R
import com.nhatbui.nutrition.ui.theme.NeutralHigh
import com.nhatbui.nutrition.ui.theme.NeutralMedium
import com.nhatbui.nutrition.ui.theme.SecondaryS400

@Composable
fun HomeScreen(
    onNavigateToFoodScan: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(horizontal = 20.dp)
    ) {
        FoodScannerBanner(
            modifier = Modifier.fillMaxWidth(),
            onNavigateToFoodScan = onNavigateToFoodScan
        )
    }
}

@Composable
private fun FoodScannerBanner(
    onNavigateToFoodScan: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .border(width = 1.dp, color = SecondaryS400)
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color(0xFFF3FAF3),
                        Color(0xFFDAEFDB)
                    )
                )
            )
            .clickable {
                onNavigateToFoodScan()
            }
            .padding(start = 16.dp, top = 12.dp, bottom = 12.dp, end = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = stringResource(R.string.food_scan_banner_title),
                style = MaterialTheme.typography.titleMedium,
                color = NeutralHigh
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(R.string.food_scan_banner_description),
                style = MaterialTheme.typography.bodySmall,
                color = NeutralMedium
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Icon(
            painter = painterResource(R.drawable.ic_scan),
            tint = Color.Unspecified,
            contentDescription = null,
            modifier = Modifier.size(40.dp)
        )
    }
}