package com.example.testtask.data.Entity.GetSchedule

data class Station (
    var code: String,
    var station_type: String,
    var station_type_name: String,
    var title: String,
    var popular_title: String,
    var short_title: String,
    var codes: Codes,
    var transport_type: String,
    var type: String
)