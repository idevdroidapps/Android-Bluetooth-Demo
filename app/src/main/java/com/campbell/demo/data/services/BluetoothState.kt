package com.campbell.demo.data.services

sealed class BluetoothState {
    object Off : BluetoothState()
    object On : BluetoothState()
    object Empty : BluetoothState()
}
