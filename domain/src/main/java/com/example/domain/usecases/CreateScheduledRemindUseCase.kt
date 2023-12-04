package com.example.domain.usecases

import com.example.domain.service.IReminderService
import javax.inject.Inject

class CreateScheduledRemindUseCase @Inject constructor(
    private val reminderService: IReminderService,
) {
    operator fun invoke(period: IReminderService.Period, msg: String) =
        reminderService.createReminder(period, msg)
}