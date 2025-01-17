package com.nhatbui.foodscan.domain.usecase

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GetFoodScanProgressUseCase {
    suspend fun execute(onResult: (Int) -> Unit) {
        withContext(Dispatchers.IO) {
            getMockProgress().collect { progress ->
                CoroutineScope(Dispatchers.Main).launch {
                    onResult(progress)
                }
            }
        }
    }

    private fun getMockProgress(): Flow<Int> = flow {
        for (progress in 0..100) {
            emit(progress)
            delay(40)
        }
    }
}
