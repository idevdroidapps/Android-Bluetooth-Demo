package com.campbell.demo.data.repositories

import com.campbell.demo.data.services.BluetoothState
import com.campbell.demo.domain.interfaces.BluetoothRepository
import com.campbell.demo.data.services.BluetoothStateBroadcastReceiver
import kotlinx.coroutines.flow.StateFlow

class BluetoothRepositoryImpl(
    private val bluetoothStateReceiver: BluetoothStateBroadcastReceiver
) : BluetoothRepository {

    override fun btAdapterState(): StateFlow<BluetoothState> {
        return bluetoothStateReceiver.btStateFlow
    }

    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: BluetoothRepositoryImpl? = null

        fun getInstance(receiver: BluetoothStateBroadcastReceiver) =
            instance ?: synchronized(this) {
                instance ?: BluetoothRepositoryImpl(receiver).also { instance = it }
            }
    }
}