package com.campbell.demo.presentation.dependency_injection

import androidx.lifecycle.ViewModelProvider
import com.campbell.demo.data.repositories.DataRepositoryImpl
import com.campbell.demo.data.services.DataService
import com.campbell.demo.domain.interfaces.DataRepository
import com.campbell.demo.domain.usecases.UseCases
import com.campbell.demo.presentation.factories.SharedViewModelFactory

object Injector {

    /**
     * Provides Data Service
     */
    private fun provideDataService(): DataService {
        return DataService()
    }

    /**
     * Provides a single source of truth ViewModels
     */
    private fun provideDataRepository(): DataRepository {
        return DataRepositoryImpl.getInstance(
            provideDataService()
        )
    }

    /**
     * Provides an instance of [UseCases]
     */
    private fun provideUseCases(): UseCases {
        return UseCases(provideDataRepository())
    }

    /**
     * Provides the [ViewModelProvider.Factory]
     */
    fun provideSharedViewModelFactory(): ViewModelProvider.Factory {
        return SharedViewModelFactory(provideUseCases())
    }
}