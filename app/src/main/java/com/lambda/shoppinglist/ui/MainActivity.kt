package com.lambda.shoppinglist.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lambda.shoppinglist.utils.NotificationGenerator
import com.lambda.shoppinglist.R
import com.lambda.shoppinglist.utils.ShoppingItemConstants
import com.lambda.shoppinglist.model.ShoppingItem
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    internal var shoppingItems: ArrayList<ShoppingItem> = ArrayList()
    companion object{
       // var shoppingList: ArrayList<String> = ArrayList()
        var shoppingList = mutableListOf<String>()
        const val NOTIFICATION_ID = 22
    }

    private val adapter = ShoppingListAdapter(shoppingItems)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val shoppingListLayout=findViewById<RecyclerView>(R.id.shopping_list_layout)
        shoppingListLayout.setHasFixedSize(false)
        val manager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        shoppingListLayout.layoutManager = manager
        shoppingListLayout.adapter = adapter
        for (i in 0 until ShoppingItemConstants.ICON_IDS.size) {
            shoppingItems.add(
                ShoppingItem(
                    ShoppingItemConstants.ITEM_NAMES_RAW[i],
                    ShoppingItemConstants.ICON_IDS[i],
                    1
                )
            )

        }
        adapter.notifyDataSetChanged()


    }
}
