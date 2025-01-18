package com.nhatbui.foodscan.presentation.ui.result.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nhatbui.foodscan.presentation.R
import com.nhatbui.foodscan.presentation.ui.result.model.NutrientDetailUiModel

private const val MAX_ITEM_IN_ROW = 2

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun OverviewSection(
    calories: Int
) {
    ResultSection(
        title = {
            Text(
                text = stringResource(R.string.overview_label),
                style = MaterialTheme.typography.titleSmall
            )
        }
    ) {
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            maxItemsInEachRow = MAX_ITEM_IN_ROW
        ) {
            ResultCard(
                modifier = Modifier.weight(1f),
                iconRes = R.drawable.ic_calories,
                title = stringResource(R.string.calories_label),
                description = stringResource(R.string.calories_description, calories)
            )
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun MacronutrientSection(
    totalGram: Int,
    nutrients: List<NutrientDetailUiModel>
) {
    NutrientSection(
        value = totalGram,
        nutrients = nutrients,
        sectionTitle = R.string.macronutrients_label,
        sectionSubtitle = R.string.macronutrients_subtitle,
        sectionDescription = R.string.macronutrients_description
    )
}

@Composable
fun MicronutrientSection(
    totalPercentage: Int,
    nutrients: List<NutrientDetailUiModel>
) {
    NutrientSection(
        value = totalPercentage,
        nutrients = nutrients,
        sectionTitle = R.string.micronutrients_label,
        sectionSubtitle = R.string.micronutrients_subtitle,
        sectionDescription = R.string.micronutrients_description
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun NutrientSection(
    value: Int,
    nutrients: List<NutrientDetailUiModel>,
    @StringRes sectionTitle: Int,
    @StringRes sectionSubtitle: Int,
    @StringRes sectionDescription: Int,
) {
    ResultSection(
        title = {
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(sectionTitle),
                style = MaterialTheme.typography.titleSmall
            )
        },
        subtitle = {
            Text(
                text = stringResource(sectionSubtitle, value),
                style = MaterialTheme.typography.labelMedium
            )
        }
    ) {
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            maxItemsInEachRow = MAX_ITEM_IN_ROW
        ) {
            nutrients.map { nutrient ->
                ResultCard(
                    modifier = Modifier.weight(1f),
                    iconRes = nutrient.iconRes,
                    title = nutrient.name(),
                    description = stringResource(sectionDescription, nutrient.value)
                )
            }
            if (nutrients.size % MAX_ITEM_IN_ROW != 0 ) {
                repeat(MAX_ITEM_IN_ROW - (nutrients.size % MAX_ITEM_IN_ROW)) {
                    Spacer(Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
private fun ResultSection(
    title: @Composable RowScope.() -> Unit,
    subtitle: @Composable RowScope.() -> Unit = {},
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 12.dp, start = 20.dp, end = 20.dp, bottom = 24.dp)
    ) {
        Row {
            title()
            subtitle()
        }
        Spacer(Modifier.height(12.dp))
        content()
    }
}
