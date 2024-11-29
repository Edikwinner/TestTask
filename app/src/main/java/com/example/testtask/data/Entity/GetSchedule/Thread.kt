package com.example.testtask.data.Entity.GetSchedule

data class Thread(
    var uid: String,
    var title: String,
    var number: String,
    var short_title: String,
    var carrier: Carrier,
    var transport_type: String,
    var vehicle: String,
    var transport_subtype: TransportSubtype,
    var express_type: String
)
