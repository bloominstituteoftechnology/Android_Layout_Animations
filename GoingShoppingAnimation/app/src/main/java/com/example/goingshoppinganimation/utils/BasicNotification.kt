package com.example.goingshoppinganimation.utils

import android.annotation.TargetApi
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build

import androidx.core.app.NotificationCompat
import com.example.goingshoppinganimation.R
import com.example.goingshoppinganimation.activity.MainActivity
import com.example.goingshoppinganimation.activity.MainActivity.Companion.CONSTANT_STRING2
import com.example.goingshoppinganimation.activity.MainActivity.Companion.NOTIFICATION_ID



object BasicNotification{




    @TargetApi(Build.VERSION_CODES.N)


    fun BasicNotification(context: Context) {

        //TODO 3
        //triggering the notification
        //regular intent to pass intent to next screen
        val contentIntent = Intent(context, MainActivity::class.java)
        // convert regular intent to pending intent

        // in () <- created from, code ,intent that's wrap and FLAG
        val pendingContentIntent = PendingIntent.getActivity(
            context,
            Build.VERSION_CODES.O,
            contentIntent,
            PendingIntent.FLAG_ONE_SHOT
        ) //can only be executed once


        //TODO 1

        //creating a channel
        val channelId = "${context.packageName}.simplechannel"

        //put the notification in the system , cast specific
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        ///if version of android matches
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val name = "Sample Notification"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val description = " Demonstrating the variables"

            // its just setting the description. description variable for my channel
            val channel = NotificationChannel(channelId, name, importance)
            channel.description = description


            //put the notification inn the channel
            notificationManager.createNotificationChannel(channel)
        }

        //TODO 2 builder pattern

        //compat specifies that older versions can run too

        //all the .set go here (below)

        val notificationBuilder = NotificationCompat.Builder(context, channelId)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setSmallIcon(android.R.drawable.ic_menu_today)
            .setContentTitle("Demo Notification")
            .setContentText("Shopping list Successfully Created!!!")
            .setAutoCancel(true) // removes notification once used
            //pending intent
            .setContentIntent(pendingContentIntent)
            .addAction(R.mipmap.ic_launcher, CONSTANT_STRING2, null)
            .setNumber(2)




        //this is the final step
        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build())

    }
}