package viewmodels

import androidx.lifecycle.ViewModel
import entities.BluetoothState
import usecases.BluetoothUseCases
import kotlinx.coroutines.flow.StateFlow

class SharedViewModel(private val bluetoothUseCases: BluetoothUseCases) : ViewModel() {

    fun btAdapterState(): StateFlow<BluetoothState> {
        return bluetoothUseCases.btAdapterState()
    }

}