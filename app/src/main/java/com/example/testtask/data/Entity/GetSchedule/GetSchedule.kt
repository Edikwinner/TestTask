package com.example.testtask.data.Entity.GetSchedule

data class GetSchedule(
    var date: String,
    var pagination: Pagination,
    var station: Station,
    var schedule: ArrayList<Schedule>,
    var schedule_direction: ScheduleDirection,
    var directions: Directions
)
