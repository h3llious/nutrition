package com.nhatbui.nutrition.di

import com.nhatbui.foodscan.domain.usecase.GetFoodScanProgressUseCase
import com.nhatbui.foodscan.domain.usecase.GetScanResultUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {
    @Provides
    fun providesGetFoodScanProgressUseCase() = GetFoodScanProgressUseCase()

    @Provides
    fun providesGetScanResultUseCase() = GetScanResultUseCase()
}