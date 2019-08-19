package com.lambdaschool.sprint2_challenge.Controller

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.lambdaschool.sprint2_challenge.Adapter.ProductAdapter

import com.lambdaschool.sprint2_challenge.Model.Product
import com.lambdaschool.sprint2_challenge.R
import com.lambdaschool.sprint2_challenge.Shopping_items.GroceryRepo
import com.lambdaschool.sprint2_challenge.Shopping_items.ShoppingItemConstants

import kotlinx.android.synthetic.main.activity_products.*
import kotlinx.android.synthetic.main.product_item.*
import javax.net.ssl.ManagerFactoryParameters

class MainActivity : AppCompatActivity() {

        companion object {
                const val NOTIF_KEY = 5

        }

        override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_products)

                GroceryRepo.createGroceryList()

                productListView.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = ProductAdapter(GroceryRepo.GroceryList)
                }

                NOTI_btn.setOnClickListener {
                        ProductNotification(purchase())
                        val sendIntent: Intent = Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(Intent.EXTRA_TEXT, "Please place this order for me: apples, ect")
                                type = "text/plain"
                        }
                        startActivity(sendIntent)

                }
        }



                fun purchase(): String {
                        var purchaseString = ""
                        for (Product in GroceryRepo.GroceryList) {
                                if (Product.purchased) purchaseString += "${Product.title}"

                        }
                        return purchaseString
                }

                fun ProductNotification(purchases: String) {

                        val channelId = "${this.packageName}.simplenotif"
                        val notificationManager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {


                                val name = "productNotification"
                                val importance = NotificationManager.IMPORTANCE_HIGH
                                val description = "this is the discription"
                                val channel = NotificationChannel(channelId, name, importance)
                                channel.description = description

                                notificationManager.createNotificationChannel(channel)
                        }

                        val notif_builder = NotificationCompat.Builder(this, channelId)
                                .setPriority(NotificationManager.IMPORTANCE_HIGH)
                                .setContentTitle("CONFIRMATION")
                                .setContentText(purchases)
                                .setSmallIcon(android.R.drawable.btn_star_big_on)

                                .setDefaults(Activity.DEFAULT_KEYS_DIALER)
                                .setAutoCancel(true)

                        notificationManager.notify(NOTIF_KEY, notif_builder.build())





                }
        }




















