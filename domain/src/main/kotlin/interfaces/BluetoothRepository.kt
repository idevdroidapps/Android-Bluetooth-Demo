package interfaces

import entities.BluetoothState
import kotlinx.coroutines.flow.StateFlow

interface BluetoothRepository {
    fun btAdapterState(): StateFlow<BluetoothState>
}