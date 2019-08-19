package com.lambdaschool.notificationdemocode.util

import android.R
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v4.app.NotificationCompat
import com.lambdaschool.sprint2_challenge.ui.MainActivity

object NotificationGenerator {
    fun orderNotification(context: Context) {
        val contentIntent = Intent(context, MainActivity::class.java)
        val pendingContentIntent = PendingIntent.getActivity(context, 0, contentIntent, PendingIntent.FLAG_ONE_SHOT)


        val channelId = "${context.packageName}.simpleChannel"
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Order Notification Channel"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val description = "To confirm order has been placed"

            val channel = NotificationChannel(channelId, name, importance)
            channel.description = description

            notificationManager.createNotificationChannel(channel)
        }

        val notificationBuilder = NotificationCompat.Builder(context, channelId)
                .setPriority(NotificationManager.IMPORTANCE_HIGH)
                .setSmallIcon(R.drawable.ic_media_ff)
                .setContentTitle("Confirmation")
                .setContentText("Your order has been placed.")
                .setAutoCancel(true)
        notificationManager.notify(6, notificationBuilder.build())
    }
}