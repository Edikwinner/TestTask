package com.example.testtask.data.Entity.GetSearch

import com.example.testtask.data.Entity.GetSearch.Segments.From
import com.example.testtask.data.Entity.GetSearch.Segments.To

data class IntervalSegments(
    var from: From,
    var thread: Thread,
    var departure_platform: String,
    var stops: String,
    var departure_terminal: String? = null,
    var to: To,
    var has_transfers: Boolean,
    var tickets_info: TicketsInfo,
    var duration: Int,
    var arrival_terminal: String? = null,
    var start_date: String,
    var arrival_platform: String
)
