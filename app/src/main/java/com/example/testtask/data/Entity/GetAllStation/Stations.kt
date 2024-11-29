package com.example.testtask.data.Entity.GetAllStation

data class Stations(
    var direction: String,
    var codes: Codes,
    var station_type: String,
    var title: String,
    var longitude: Double,
    var transport_type: String,
    var latitude: Double
)
