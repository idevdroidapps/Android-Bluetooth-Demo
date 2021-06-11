package com.campbell.demo.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.campbell.demo.data.services.BluetoothState
import com.campbell.demo.domain.usecases.UseCases
import kotlinx.coroutines.flow.StateFlow

class SharedViewModel(private val useCases: UseCases) : ViewModel() {

    fun btAdapterState(): StateFlow<BluetoothState> {
        return useCases.btAdapterState()
    }

}