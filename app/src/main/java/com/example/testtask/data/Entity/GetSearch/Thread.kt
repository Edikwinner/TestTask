package com.example.testtask.data.Entity.GetSearch

data class Thread(
    var uid: String,
    var title: String,
    var interval: Interval,
    var number: String,
    var short_title: String,
    var thread_method_link: String,
    var carrier: Carrier,
    var transport_type: String,
    var vehicle: String,
    var transport_subtype: TransportSubtype,
    var express_type: String
)
