package com.campbell.demo.domain.usecases

import com.campbell.demo.data.services.BluetoothState
import com.campbell.demo.domain.interfaces.DataRepository
import kotlinx.coroutines.flow.StateFlow

class UseCases(private val dataRepository: DataRepository) {

    fun btAdapterState(): StateFlow<BluetoothState> {
        return dataRepository.btAdapterState()
    }
}