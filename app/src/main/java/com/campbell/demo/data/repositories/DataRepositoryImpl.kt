package com.campbell.demo.data.repositories

import com.campbell.demo.data.services.BluetoothState
import com.campbell.demo.domain.interfaces.DataRepository
import com.campbell.demo.data.services.BtStateBroadcastReceiver
import kotlinx.coroutines.flow.StateFlow

class DataRepositoryImpl(
    private val bluetoothStateReceiver: BtStateBroadcastReceiver
) : DataRepository {

    override fun btAdapterState(): StateFlow<BluetoothState> {
        return bluetoothStateReceiver.btStateFlow
    }

    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: DataRepositoryImpl? = null

        fun getInstance(receiver: BtStateBroadcastReceiver) =
            instance ?: synchronized(this) {
                instance ?: DataRepositoryImpl(receiver).also { instance = it }
            }
    }
}