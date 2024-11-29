package com.example.testtask.domain.UseCase

import android.util.Log
import com.example.testtask.data.Entity.GetSearch.Segments.From
import com.example.testtask.data.Entity.GetSearch.Segments.To
import com.example.testtask.domain.Entity.Response
import com.example.testtask.domain.Repository.APIRepository
import java.text.DateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class GetResponseUseCase(private val repository: APIRepository) {
    private lateinit var response: Response

    suspend fun getSearchResponse(
        from: String,
        to: String,
        date: String,
        transportType: String
    ): ArrayList<Response> {
        val searchResponse = repository.getSearch(
            from = from,
            to = to,
            date = date,
            transportTypes = transportType)
        val responses: ArrayList<Response> = arrayListOf()
        searchResponse?.segments?.forEach { it ->
            when(it.thread.transport_type){
                "plane" -> {
                    response = Response(
                        transportType = it.thread.transport_type,
                        tripTitle = it.thread.title,
                        tripNumber = it.thread.number,
                        tripTransportName = it.thread.vehicle ,
                        arrivalDate = getCorrectDate(it.departure),
                        arrivalTime = getCorrectTime(it.departure),
                        arrivalTitle = it.from.title,
                        arrivalTerminal = getCorrectTerminal(it.departure_terminal),
                        tripTime = getHoursAndMinutes(it.duration),
                        departureDate = getCorrectDate(it.arrival),
                        departureTime = getCorrectTime(it.arrival),
                        departureTitle = it.to.title,
                        departureTerminal = getCorrectTerminal(it.arrival_terminal),
                    )
                }
               "train" -> {
                    response = Response(
                        transportType = it.thread.transport_type,
                        tripTitle = it.thread.title,
                        tripNumber = it.thread.number,
                        tripTransportName = it.thread.carrier.title,
                        arrivalDate = getCorrectDate(it.departure),
                        arrivalTime = getCorrectTime(it.departure),
                        arrivalTitle = getCorrectTitleFrom(it.from),
                        arrivalTerminal = "",
                        tripTime = getHoursAndMinutes(it.duration),
                        departureDate = getCorrectDate(it.arrival),
                        departureTime = getCorrectTime(it.arrival),
                        departureTitle = getCorrectTitleTo(it.to),
                        departureTerminal = "",
                    )
                }
                "suburban" -> {
                    response = Response(
                        transportType = it.thread.transport_type,
                        tripTitle = it.thread.title,
                        tripNumber = it.thread.number,
                        tripTransportName = "",
                        arrivalDate = getCorrectDate(it.departure),
                        arrivalTime = getCorrectTime(it.departure),
                        arrivalTitle = it.from.title,
                        arrivalTerminal = "",
                        tripTime = getHoursAndMinutes(it.duration),
                        departureDate = getCorrectDate(it.arrival),
                        departureTime = getCorrectTime(it.arrival),
                        departureTitle = it.to.title,
                        departureTerminal = "",
                    )
                }
                "bus" -> {
                    response = Response(
                        transportType = it.thread.transport_type,
                        tripTitle = it.thread.title,
                        tripNumber = "",
                        tripTransportName = it.thread.carrier.title,
                        arrivalDate = getCorrectDate(it.departure),
                        arrivalTime = getCorrectTime(it.departure),
                        arrivalTitle = getCorrectTitleFrom(it.from),
                        arrivalTerminal = "",
                        tripTime = getHoursAndMinutes(it.duration),
                        departureDate = getCorrectDate(it.arrival),
                        departureTime = getCorrectTime(it.arrival),
                        departureTitle = getCorrectTitleTo(it.to),
                        departureTerminal = "",
                    )
                }
            }
            responses.add(response)
        }
        return responses

    }


    fun getCorrectDate(date: String): String{
        return LocalDateTime
            .parse(date, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
            .format(DateTimeFormatter.ofPattern("d MMM"))
    }

    fun getCorrectTime(date: String): String{
        return LocalDateTime
            .parse(date, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
            .format(DateTimeFormatter.ofPattern("HH:mm"))
    }

    fun getHoursAndMinutes(seconds: Long): String{
        val hours = seconds / 3600
        val minutes = (seconds % 3600) / 60
        if (minutes < 10){
            return "$hours:0$minutes"
        }
        else{
            return "$hours:$minutes"
        }
    }

    fun getCorrectTitleFrom(from: From): String{
        return if(!from.popular_title.isNullOrBlank()){
            from.popular_title!!
        } else{
            from.title
        }
    }

    fun getCorrectTitleTo(to: To): String{
        return if(!to.popular_title.isNullOrBlank()){
            to.popular_title!!
        } else{
            to.title
        }
    }

    fun getCorrectTerminal(terminal: String): String{
        return if(terminal == "NULL") {
            ""
        } else{
            terminal
        }
    }

}