package com.example.domain.service

interface IReminderService {
    fun createReminder(period: Period, message: String, id: Int)

    enum class Period {
        QUARTER_HOUR, HOUR, DAY, WEEK, MINUTE, FIVE_SECS;
    }
}