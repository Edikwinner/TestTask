package com.example.testtask.common.DI

import com.example.testtask.domain.Repository.APIRepository
import com.example.testtask.data.Repository.APIRepositoryImpl
import com.example.testtask.domain.UseCase.GetAllStationUseCase
import com.example.testtask.domain.UseCase.GetResponseUseCase
import com.example.testtask.presentation.Fragments.MainFragment.MainViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single <APIRepository> { APIRepositoryImpl() }
    factory <GetResponseUseCase> { GetResponseUseCase(get()) }
    factory <GetAllStationUseCase> { GetAllStationUseCase(get()) }
    viewModel<MainViewModel> { MainViewModel(get(), get()) }
}