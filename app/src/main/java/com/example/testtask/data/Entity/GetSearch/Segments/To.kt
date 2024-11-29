package com.example.testtask.data.Entity.GetSearch.Segments

data class To(
    var code: String,
    var title: String,
    var station_type: String,
    var station_type_name: String,
    var popular_title: String? = null,
    var short_title: String? = null,
    var transport_type: String,
    var type: String
)
