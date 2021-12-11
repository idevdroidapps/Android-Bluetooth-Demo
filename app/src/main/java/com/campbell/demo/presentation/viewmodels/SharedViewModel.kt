package com.campbell.demo.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.campbell.demo.data.services.BluetoothState
import com.campbell.demo.domain.usecases.BluetoothUseCases
import kotlinx.coroutines.flow.StateFlow

class SharedViewModel(private val bluetoothUseCases: BluetoothUseCases) : ViewModel() {

    fun btAdapterState(): StateFlow<BluetoothState> {
        return bluetoothUseCases.btAdapterState()
    }

}