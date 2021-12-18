package entities

sealed class BluetoothState {
    object Off : BluetoothState()
    object On : BluetoothState()
    object Empty : BluetoothState()
}
