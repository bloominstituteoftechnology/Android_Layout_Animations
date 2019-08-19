package com.lambdaschool.sprint2_challenge.ui

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.transition.Explode
import android.view.Window
import com.lambdaschool.sprint2_challenge.listOfItem.ShoppingList
import com.lambdaschool.sprint2_challenge.R
import com.lambdaschool.sprint2_challenge.util.ShoppingListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import com.lambdaschool.notificationdemocode.util.NotificationGenerator
import com.lambdaschool.sprint2_challenge.model.ShoppingListModel as ShoppingListModel


class MainActivity : AppCompatActivity() {
    companion object{
        const val REQUEST_CODE = 2
        val List = mutableListOf<ShoppingListModel>()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        window.enterTransition = Explode()
        window.exitTransition = Explode()

        setContentView(R.layout.activity_main)

        ShoppingList.createShoppingList()

        shopping_list_view.apply{
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = ShoppingListAdapter(ShoppingList.List)
        }

        choose_button.setOnClickListener {
            NotificationGenerator.orderNotification(this)
        }
    }
}