package com.example.intersvyaztestapp.services

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.example.domain.service.IReminderService
import com.example.intersvyaztestapp.services.receivers.AlarmReceiver
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.Calendar
import javax.inject.Inject

class ReminderService @Inject constructor(
    @ApplicationContext private val context: Context,
): IReminderService {
    companion object {
        const val MESSAGE_EXTRA_KEY = "msg"
        const val ID_EXTRA_KEY = "id"
    }

    override fun createReminder(period: IReminderService.Period, message: String, id: Int) {
        val alarmService = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val destDate = Calendar.getInstance()
        when (period) {
            IReminderService.Period.QUARTER_HOUR -> destDate.add(Calendar.MINUTE, 15)
            IReminderService.Period.HOUR -> destDate.add(Calendar.HOUR, 1)
            IReminderService.Period.DAY -> destDate.add(Calendar.HOUR, 24)
            IReminderService.Period.WEEK -> destDate.add(Calendar.HOUR, 24 * 7)
            IReminderService.Period.MINUTE -> destDate.add(Calendar.SECOND, 60)
            IReminderService.Period.FIVE_SECS -> destDate.add(Calendar.SECOND, 5)
        }
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra(MESSAGE_EXTRA_KEY, message)
            putExtra(ID_EXTRA_KEY, id)
        }
        alarmService.set(
            AlarmManager.RTC_WAKEUP,
            destDate.timeInMillis,
            PendingIntent.getBroadcast(context, message.hashCode(), intent, PendingIntent.FLAG_IMMUTABLE)
        )
    }
}