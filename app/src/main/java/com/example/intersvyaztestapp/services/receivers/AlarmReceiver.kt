package com.example.intersvyaztestapp.services.receivers

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.intersvyaztestapp.R
import com.example.intersvyaztestapp.services.ReminderService
import com.example.intersvyaztestapp.ui.MainActivity

class AlarmReceiver: BroadcastReceiver() {
    companion object {
        private const val CHANNEL_ID = "com.example.intersvyaztestapp.notification_channel"
        private const val NOTIFICATION_ID = 101
    }

    @SuppressLint("MissingPermission")
    override fun onReceive(context: Context?, intent: Intent?) {
        val message = intent?.getStringExtra(ReminderService.MESSAGE_EXTRA_KEY) ?: return
        val id = intent.getIntExtra(ReminderService.ID_EXTRA_KEY, -1).takeIf { it >= 0 } ?: return
        context ?: return
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val targetIntent = Intent(context, MainActivity::class.java).apply {
            putExtra(ReminderService.ID_EXTRA_KEY, id)
        }
        val pending = PendingIntent.getActivity(context, id.hashCode(), targetIntent, PendingIntent.FLAG_IMMUTABLE)
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ph_movie)
            .setContentTitle("Premiers app alarm")
            .setContentText(message)
            .setContentIntent(pending)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, "Alarms", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(NOTIFICATION_ID, builder.build())
    }
}