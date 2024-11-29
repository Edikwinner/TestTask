package com.example.testtask.presentation.Fragments.MainFragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtask.data.Entity.GetAllStation.GetAllStation
import com.example.testtask.domain.Entity.Response
import com.example.testtask.domain.UseCase.GetAllStationUseCase
import com.example.testtask.domain.UseCase.GetResponseUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Locale

class MainViewModel(
    private val getResponseUseCase: GetResponseUseCase,
    private val getAllStationUseCase: GetAllStationUseCase
    ) : ViewModel() {

    val response = MutableLiveData<ArrayList<Response>>()

    val allStationResponse = MutableLiveData<GetAllStation>(null)

    init {
        viewModelScope.launch {
            allStationResponse.value = getAllStationUseCase.getAllStation()
        }
    }

    fun getSearch(
        from: String,
        to: String,
        date: String,
        transportType: String = ""
    ){
        viewModelScope.launch {
            response.value = getResponseUseCase.getSearchResponse(
                from = getCodeFromName(from),
                to = getCodeFromName(to),
                date = date,
                transportType = transportType
            )
        }
    }

    fun getCodeFromName(name: String): String{
        allStationResponse.value?.countries?.forEach { country ->
            country.regions.forEach { region ->
                region.settlements.forEach { settlements ->
                    if(settlements.title.lowercase() == name.lowercase()){
                        return settlements.codes.yandex_code
                    }
                }
            }
        }
        return ""
    }

}