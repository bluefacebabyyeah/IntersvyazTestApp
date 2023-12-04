package com.example.intersvyaztestapp.services.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.intersvyaztestapp.services.ReminderService

class AlarmReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val message = intent?.getStringExtra(ReminderService.MESSAGE_EXTRA_KEY) ?: ""
    }
}