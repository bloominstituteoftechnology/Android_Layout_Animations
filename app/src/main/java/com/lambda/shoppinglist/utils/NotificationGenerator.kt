package com.lambda.shoppinglist.utils

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import com.lambda.shoppinglist.ui.MainActivity

object NotificationGenerator {
    fun orderNotification(context: Context) {



        val channelId = "${context.packageName}.orderNotification"
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Simple Notification Channel"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val description = "Demonstrating creation of notification channels"

            val channel = NotificationChannel(channelId, name, importance)
            channel.description = description

            notificationManager.createNotificationChannel(channel)
        }

        val notificationBuilder = NotificationCompat.Builder(context, channelId)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setSmallIcon(android.R.drawable.presence_online)
            .setContentTitle("Confirmation")
            .setContentText("Your order has been placed")
            .setDefaults(Notification.DEFAULT_SOUND)
            .setDefaults(Notification.FLAG_AUTO_CANCEL)
            .setAutoCancel(true)

        notificationManager.notify(MainActivity.NOTIFICATION_ID, notificationBuilder.build())
    }
}