package com.example.domain.service

interface IReminderService {
    fun createReminder(period: Period, message: String)

    enum class Period {
        QUARTER_HOUR, HOUR, DAY, WEEK;
    }
}