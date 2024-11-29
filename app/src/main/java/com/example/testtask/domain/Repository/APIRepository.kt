package com.example.testtask.domain.Repository

import com.example.testtask.data.Entity.GetAllStation.GetAllStation
import com.example.testtask.data.Entity.GetSchedule.GetSchedule
import com.example.testtask.data.Entity.GetSearch.GetSearch

interface APIRepository {
    suspend fun getAllStation(): GetAllStation?

    suspend fun getSearch(
        from: String,
        to: String,
        date: String,
        transportTypes: String
    ): GetSearch?
}