package com.nhatbui.foodscan.presentation.ui.result

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.nhatbui.common.ui.HeaderBar
import com.nhatbui.foodscan.presentation.R
import com.nhatbui.foodscan.presentation.presentation.ScanResultViewModel
import com.nhatbui.foodscan.presentation.ui.result.component.MacronutrientSection
import com.nhatbui.foodscan.presentation.ui.result.component.MicronutrientSection
import com.nhatbui.foodscan.presentation.ui.result.component.OverviewSection
import com.nhatbui.foodscan.presentation.ui.result.component.SegmentedCircularChart
import com.nhatbui.foodscan.presentation.ui.result.mapper.NutrientDetailsToCircularSegmentMapper
import com.nhatbui.foodscan.presentation.ui.result.mapper.NutrientUiMapper
import com.nhatbui.foodscan.presentation.ui.result.model.NutrientsUiModel
import com.nhatbui.foodscan.presentation.ui.result.model.ResultInfoTitleUiModel

@Composable
fun ScanResultScreen(
    scanTargetUri: String,
    onNavigateBack: () -> Unit
) {
    val viewModel = hiltViewModel<ScanResultViewModel>()
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    val nutrientUiMapper = remember { NutrientUiMapper() }
    val macronutrients = remember(viewState.macronutrients) {
        nutrientUiMapper.map(viewState.macronutrients)
    }
    val micronutrients = remember(viewState.micronutrients) {
        nutrientUiMapper.map(viewState.micronutrients)
    }

    LaunchedEffect(Unit) {
        viewModel.getScanResult()
    }

    ScanResultActionBar(onNavigateBack)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        ResultHeaderInfo(scanTargetUri, viewState.type, viewState.name)
        OverviewSection(calories = viewState.nutritionInCalories)
        MacronutrientSection(macronutrients.total, macronutrients.details)
        MicronutrientSection(micronutrients.total, micronutrients.details)
        NutrientSummaryChart(
            macronutrients = macronutrients,
            micronutrients = micronutrients
        )
        SaveDailyLogButton()
        PremiumPrompt()
    }
}

@Composable
fun NutrientSummaryChart(
    macronutrients: NutrientsUiModel,
    micronutrients: NutrientsUiModel,
    modifier: Modifier = Modifier
) {
    val circularSegmentUiMapper = remember { NutrientDetailsToCircularSegmentMapper() }
    Box(
        modifier = modifier.fillMaxWidth().padding(bottom = 20.dp),
        contentAlignment = Alignment.Center
    ) {
        SegmentedCircularChart(
            modifier = Modifier.size(144.dp),
            totalWeight = macronutrients.total,
            segmentData = circularSegmentUiMapper.map(macronutrients),
            strokeWidth = 12.dp
        )
        SegmentedCircularChart(
            modifier = Modifier.size(110.dp),
            totalWeight = 100,
            segmentData = circularSegmentUiMapper.map(micronutrients),
            strokeWidth = 12.dp
        )
    }
}

@Composable
fun PremiumPrompt() {
    Spacer(Modifier.height(12.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.premium_prompt_description),
            fontWeight = FontWeight.W400,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            color = Color(0xFF565656)
        )
        Spacer(Modifier.width(4.dp))
        Text(
            text = stringResource(R.string.premium_prompt_action),
            fontWeight = FontWeight.W500,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            color = Color(0xFF141414),
            textDecoration = TextDecoration.Underline
        )
    }
}

@Composable
private fun SaveDailyLogButton(modifier: Modifier = Modifier) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFFFBF62),
                        Color(0xFFFFA726)
                    )
                ),
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = 1.dp, brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFE29523),
                        Color(0xFF98671E)
                    )
                ),
                shape = RoundedCornerShape(12.dp)
            ),
        onClick = {},
        shape = RoundedCornerShape(12.dp),
        contentPadding = PaddingValues(vertical = 12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color.Black
        )
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = stringResource(R.string.scan_save_result_action),
            fontWeight = FontWeight.W500,
            fontSize = 16.sp,
            lineHeight = 20.sp
        )
    }
}

@Composable
private fun ResultHeaderInfo(
    scanTargetUri: String,
    type: String,
    name: String
) {
    Box(
        Modifier
            .fillMaxWidth()
            .height(268.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = Uri.parse(scanTargetUri),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        ResultInfoTitle(
            modifier = Modifier
                .fillMaxWidth()
                .height(108.dp),
            info = ResultInfoTitleUiModel(type, name)
        )
    }
}

@Composable
fun ResultInfoTitle(
    info: ResultInfoTitleUiModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0x00FFFFFF),
                        Color(0xADFFFFFF),
                        Color(0xFFFFFFFF)
                    )
                )
            )
            .padding(horizontal = 16.dp, vertical = 20.dp)
    ) {
        Spacer(modifier.weight(1f))
        Text(
            modifier = Modifier
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(24.dp)
                )
                .padding(horizontal = 6.dp, vertical = 3.dp),
            text = info.type,
            fontSize = 10.sp,
            lineHeight = 10.sp,
            letterSpacing = 0.04.sp,
            fontWeight = FontWeight.W400
        )
        Spacer(Modifier.height(2.dp))
        Text(
            text = info.name,
            fontWeight = FontWeight.W600,
            fontSize = 20.sp,
            lineHeight = 24.sp,
        )
    }
}

@Composable
private fun ScanResultActionBar(onNavigateBack: () -> Unit) {
    HeaderBar(
        modifier = Modifier
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF191818),
                        Color(0x00191818)
                    )
                )
            )
            .zIndex(1f),
        title = stringResource(R.string.result_title),
        titleColor = Color.White,
        onNavigateBack = onNavigateBack
    )
}
