package com.example.testtask.data.Repository

import android.util.Log
import com.example.testtask.data.APIInterface
import com.example.testtask.domain.Repository.APIRepository
import com.example.testtask.data.Entity.GetAllStation.GetAllStation
import com.example.testtask.data.Entity.GetSchedule.GetSchedule
import com.example.testtask.data.Entity.GetSearch.GetSearch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class APIRepositoryImpl: APIRepository {
    private val apiInterface = Retrofit
        .Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://api.rasp.yandex.net/v3.0/")
        .build()
        .create(APIInterface::class.java)

    override suspend fun getAllStation(): GetAllStation? = suspendCoroutine { continuation ->
        apiInterface.getAllStation().enqueue(object : Callback<GetAllStation> {
            override fun onFailure(p0: Call<GetAllStation>, p1: Throwable) {
                Log.i("TAG", "onFailure: ${p1.message}")
                continuation.resume(null)
            }

            override fun onResponse(
                p0: Call<GetAllStation>,
                p1: Response<GetAllStation>
            ) {
                continuation.resume(p1.body())
            }
        })
    }


    override suspend fun getSearch(
        from: String,
        to: String,
        date: String,
        transportTypes: String
    ): GetSearch? = suspendCoroutine { continuation ->
        apiInterface.getSearch(
            from,
            to,
            date,
            transportTypes
        ).enqueue(object: Callback<GetSearch> {
            override fun onFailure(p0: Call<GetSearch>, p1: Throwable) {
                Log.i("TAG", "onFailure: ${p1.message}")
                continuation.resume(null)
            }

            override fun onResponse(
                p0: Call<GetSearch>,
                p1: Response<GetSearch>
            ) {
                continuation.resume(p1.body())
            }
        })
    }

}