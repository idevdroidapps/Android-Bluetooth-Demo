package com.campbell.demo.presentation.dependency_injection

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.IntentFilter
import androidx.lifecycle.ViewModelProvider
import com.campbell.demo.data.repositories.DataRepositoryImpl
import com.campbell.demo.domain.interfaces.DataRepository
import com.campbell.demo.domain.usecases.UseCases
import com.campbell.demo.presentation.factories.SharedViewModelFactory
import com.campbell.demo.data.services.BtStateBroadcastReceiver

object Injector {

    /**
     * Provides BluetoothState BroadcastReceiver
     */
    private fun provideBtStateReceiver(activity: Activity): BtStateBroadcastReceiver {
        val receiver = BtStateBroadcastReceiver()
        activity.registerReceiver(receiver, IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED))
        return receiver
    }

    /**
     * Provides a single source of truth ViewModels
     */
    private fun provideDataRepository(activity: Activity): DataRepository {
        return DataRepositoryImpl.getInstance(
            provideBtStateReceiver(activity)
        )
    }

    /**
     * Provides an instance of [UseCases]
     */
    private fun provideUseCases(activity: Activity): UseCases {
        return UseCases(provideDataRepository(activity))
    }

    /**
     * Provides the [ViewModelProvider.Factory]
     */
    fun provideSharedViewModelFactory(activity: Activity): ViewModelProvider.Factory {
        return SharedViewModelFactory(provideUseCases(activity))
    }
}