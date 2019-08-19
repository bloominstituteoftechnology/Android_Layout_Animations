package com.lambdaschool.sprint2_challenge.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
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
        setContentView(R.layout.activity_main)

        ShoppingList.createShoppingList()

        shopping_list_view.apply{
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = ShoppingListAdapter(ShoppingList.List)
        }

        choose_button.setOnClickListener{ j0 ->
                NotificationGenerator.orderNotification(this)
            var choseItem = 1
            var sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "I want to order $choseItem")
                type = "text/plain"
            }
            startActivityForResult(sendIntent,REQUEST_CODE)

        }
    }
}