package com.campbell.demo.domain.usecases

import com.campbell.demo.data.services.BluetoothState
import com.campbell.demo.domain.interfaces.BluetoothRepository
import kotlinx.coroutines.flow.StateFlow

class BluetoothUseCases(private val bluetoothRepository: BluetoothRepository) {

    fun btAdapterState(): StateFlow<BluetoothState> {
        return bluetoothRepository.btAdapterState()
    }

}