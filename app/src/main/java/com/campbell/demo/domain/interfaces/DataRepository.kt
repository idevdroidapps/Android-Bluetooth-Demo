package com.campbell.demo.domain.interfaces

import com.campbell.demo.domain.entities.Data

interface DataRepository {
    fun getData(): Data
}