package com.example.testtask.domain.Entity

data class Response(
    var transportType: String? = "",
    var tripTitle: String = "",
    var tripNumber: String = "",
    var tripTransportName: String? = "",
    var arrivalDate: String = "",
    var arrivalTime: String = "",
    var arrivalTitle: String = "",
    var arrivalTerminal: String = "",
    var tripTime: String = "",
    var departureDate: String = "",
    var departureTime: String = "",
    var departureTitle: String = "",
    var departureTerminal: String = "",
)
