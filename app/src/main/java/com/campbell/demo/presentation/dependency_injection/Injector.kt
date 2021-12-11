package com.campbell.demo.presentation.dependency_injection

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.IntentFilter
import androidx.lifecycle.ViewModelProvider
import com.campbell.demo.data.repositories.BluetoothRepositoryImpl
import com.campbell.demo.domain.interfaces.BluetoothRepository
import com.campbell.demo.domain.usecases.BluetoothUseCases
import com.campbell.demo.presentation.factories.SharedViewModelFactory
import com.campbell.demo.data.services.BluetoothStateBroadcastReceiver

object Injector {

    /**
     * Provides BluetoothState BroadcastReceiver
     */
    private fun provideBtStateReceiver(activity: Activity): BluetoothStateBroadcastReceiver {
        val receiver = BluetoothStateBroadcastReceiver()
        activity.registerReceiver(receiver, IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED))
        return receiver
    }

    /**
     * Provides a single source of truth ViewModels
     */
    private fun provideDataRepository(activity: Activity): BluetoothRepository {
        return BluetoothRepositoryImpl.getInstance(
            provideBtStateReceiver(activity)
        )
    }

    /**
     * Provides an instance of [BluetoothUseCases]
     */
    private fun provideUseCases(activity: Activity): BluetoothUseCases {
        return BluetoothUseCases(provideDataRepository(activity))
    }

    /**
     * Provides the [ViewModelProvider.Factory]
     */
    fun provideSharedViewModelFactory(activity: Activity): ViewModelProvider.Factory {
        return SharedViewModelFactory(provideUseCases(activity))
    }
}