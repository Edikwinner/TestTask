package com.example.testtask.data.Entity.GetSearch

import com.example.testtask.data.Entity.GetSearch.Search.Search
import com.example.testtask.data.Entity.GetSearch.Segments.Segments

data class GetSearch(
    var pagination: Pagination,
    var interval_segments: ArrayList<IntervalSegments>,
    var segments: ArrayList<Segments>,
    var search: Search
)
