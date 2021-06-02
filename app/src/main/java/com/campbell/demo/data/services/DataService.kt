package com.campbell.demo.data.services

import com.campbell.demo.domain.entities.Data
import java.util.*

class DataService {

    fun getData(): Data {
        return Data(UUID.randomUUID().toString())
    }
}