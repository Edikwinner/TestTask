package com.example.testtask.data.Entity.GetSearch.Segments

import com.example.testtask.data.Entity.GetSearch.Thread
import com.example.testtask.data.Entity.GetSearch.TicketsInfo

data class Segments(
    var arrival: String,
    var from: From,
    var thread: Thread,
    var departure_platform: String,
    var departure: String,
    var stops: String,
    var departure_terminal: String,
    var to: To,
    var has_transfers: Boolean,
    var tickets_info: TicketsInfo,
    var duration: Long,
    var arrival_terminal: String,
    var start_date: String,
    var arrival_platform: String
)
