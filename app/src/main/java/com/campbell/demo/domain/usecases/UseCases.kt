package com.campbell.demo.domain.usecases

import com.campbell.demo.domain.entities.Data
import com.campbell.demo.domain.interfaces.DataRepository

class UseCases(private val dataRepository: DataRepository) {

    fun getData(): Data {
        return dataRepository.getData()
    }
}