package com.example.testtask.data

import com.example.testtask.data.Entity.GetAllStation.GetAllStation
import com.example.testtask.data.Entity.GetSearch.GetSearch
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "3bb21e9a-215b-4ced-b8d0-a0d5b37fa450"

interface APIInterface {

    @GET("search/?apikey=$API_KEY")
    fun getSearch(
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("date") date: String,
        @Query("transport_types") transportTypes: String
    ): Call<GetSearch>

    @GET("stations_list/?apikey=$API_KEY")
    fun getAllStation(): Call<GetAllStation>
}