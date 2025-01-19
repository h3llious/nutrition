package com.nhatbui.streak.domain.mapper

import com.nhatbui.streak.domain.model.MilestoneDomainModel
import com.nhatbui.streak.domain.model.StreakDateDomainModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters
import java.time.LocalDate

@RunWith(Parameterized::class)
class StreakDatesGeneratorTest(
    private val inputCurrentStreakCount: Int,
    private val inputIsCurrentDateInStreak: Boolean,
    private val inputTargetMilestone: MilestoneDomainModel,
    private val outputDates: List<StreakDateDomainModel>
) {
    companion object {

        @JvmStatic
        @Parameters(name = "Given member {0}, {1}, {2} then returns expectedResult {3}")
        fun parameters() = listOf(
            arrayOf(
                5,
                false,
                MilestoneDomainModel.TenDayMilestone(false),
                listOf(
                    StreakDateDomainModel.NoStreakDate(6), // Monday Jan 6
                    StreakDateDomainModel.LoggedStreak(7),
                    StreakDateDomainModel.LoggedStreak(8),
                    StreakDateDomainModel.LoggedStreak(9),
                    StreakDateDomainModel.LoggedStreak(10),
                    StreakDateDomainModel.LoggedStreak(11),
                    StreakDateDomainModel.CurrentDate(12), // Current Sunday Jan 12
                    StreakDateDomainModel.DateInTargetStreak(13),
                    StreakDateDomainModel.DateInTargetStreak(14),
                    StreakDateDomainModel.DateInTargetStreak(15),
                    StreakDateDomainModel.DateInTargetStreak(16),
                    StreakDateDomainModel.NoStreakDate(17),
                    StreakDateDomainModel.NoStreakDate(18),
                    StreakDateDomainModel.NoStreakDate(19)
                )
            )
        )
    }

    private lateinit var classUnderTest: StreakDatesGenerator

    @Before
    fun setup() {
        classUnderTest = StreakDatesGenerator(
            now = LocalDate.of(2025, 1, 12)
        )
    }

    @Test
    fun `When map then returns expected result`() {
        // When
        val actualValue = classUnderTest.generate(
            inputCurrentStreakCount,
            inputIsCurrentDateInStreak,
            inputTargetMilestone
        )

        // Then
        assertEquals(outputDates, actualValue)
    }
}
