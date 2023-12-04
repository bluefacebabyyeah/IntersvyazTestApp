package com.example.intersvyaztestapp.services.receivers

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.intersvyaztestapp.R
import com.example.intersvyaztestapp.services.ReminderService

class AlarmReceiver: BroadcastReceiver() {
    companion object {
        private const val CHANNEL_ID = "com.example.intersvyaztestapp.notification_channel"
        private const val NOTIFICATION_ID = 101
    }

    @SuppressLint("MissingPermission")
    override fun onReceive(context: Context?, intent: Intent?) {
        val message = intent?.getStringExtra(ReminderService.MESSAGE_EXTRA_KEY) ?: return
        context ?: return
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ph_movie)
            .setContentTitle("Premiers app alarm")
            .setContentText(message)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, "Alarms", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(NOTIFICATION_ID, builder.build())
    }
}