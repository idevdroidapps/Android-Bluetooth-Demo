package com.campbell.demo.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.campbell.demo.domain.entities.Data
import com.campbell.demo.domain.usecases.UseCases

class SharedViewModel(private val useCases: UseCases): ViewModel() {

    fun getData(): Data {
        return useCases.getData()
    }
}