package factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import usecases.BluetoothUseCases
import viewmodels.SharedViewModel

class SharedViewModelFactory(private val bluetoothUseCases: BluetoothUseCases) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SharedViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SharedViewModel(bluetoothUseCases) as T
        }
        throw IllegalArgumentException("Illegal ViewModel Class")
    }
}