package com.campbell.demo.data.repositories

import com.campbell.demo.data.services.DataService
import com.campbell.demo.domain.entities.Data
import com.campbell.demo.domain.interfaces.DataRepository

class DataRepositoryImpl(private val dataService: DataService) : DataRepository {

    override fun getData(): Data {
        return dataService.getData()
    }

    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: DataRepositoryImpl? = null

        fun getInstance(service: DataService) =
            instance ?: synchronized(this) {
                instance ?: DataRepositoryImpl(service).also { instance = it }
            }
    }
}