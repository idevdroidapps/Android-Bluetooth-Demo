package usecases

import entities.BluetoothState
import interfaces.BluetoothRepository
import kotlinx.coroutines.flow.StateFlow

class BluetoothUseCases(private val bluetoothRepository: BluetoothRepository) {

    fun btAdapterState(): StateFlow<BluetoothState> {
        return bluetoothRepository.btAdapterState()
    }

}