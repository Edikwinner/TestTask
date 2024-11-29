package com.example.testtask.domain.UseCase

import com.example.testtask.data.Entity.GetAllStation.GetAllStation
import com.example.testtask.domain.Repository.APIRepository

class GetAllStationUseCase(private val repository: APIRepository) {
    suspend fun getAllStation(): GetAllStation?{
        return repository.getAllStation()
    }
}