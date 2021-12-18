package repositories

import entities.BluetoothState
import interfaces.BluetoothRepository
import kotlinx.coroutines.flow.StateFlow
import receivers.BluetoothStateBroadcastReceiver

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