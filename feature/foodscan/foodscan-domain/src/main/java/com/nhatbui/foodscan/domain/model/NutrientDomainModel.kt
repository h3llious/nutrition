package com.nhatbui.foodscan.domain.model

sealed class NutrientDomainModel(
    open val value: Int
) {
    data class Proteins(
        override val value: Int,
    ) : NutrientDomainModel(
        value = value
    )

    data class Carbs(
        override val value: Int,
    ) : NutrientDomainModel(
        value = value
    )

    data class Fats(
        override val value: Int,
    ) : NutrientDomainModel(
        value = value
    )

    data class VitaminA(
        override val value: Int,
    ) : NutrientDomainModel(
        value = value
    )

    data class Calcium(
        override val value: Int,
    ) : NutrientDomainModel(
        value = value
    )
}
