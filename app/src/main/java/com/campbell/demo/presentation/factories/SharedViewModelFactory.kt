package com.campbell.demo.presentation.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.campbell.demo.domain.usecases.UseCases
import com.campbell.demo.presentation.viewmodels.SharedViewModel

class SharedViewModelFactory(private val useCases: UseCases) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SharedViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SharedViewModel(useCases) as T
        }
        throw IllegalArgumentException("Illegal ViewModel Class")
    }
}