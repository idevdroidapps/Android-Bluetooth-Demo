package com.campbell.demo.domain.interfaces

import com.campbell.demo.data.services.BluetoothState
import kotlinx.coroutines.flow.StateFlow

interface BluetoothRepository {
    fun btAdapterState(): StateFlow<BluetoothState>
}