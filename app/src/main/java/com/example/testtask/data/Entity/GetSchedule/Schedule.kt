package com.example.testtask.data.Entity.GetSchedule

data class Schedule(
    var except_days: String,
    var arrival: String,
    var thread: Thread,
    var is_fuzzy: Boolean,
    var days: String,
    var stops: String,
    var departure: String,
    var terminal: String,
    var platform: String
)
