package com.example.goingshoppinganimation.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.goingshoppinganimation.R
import com.example.goingshoppinganimation.adapter.ShoppingListAdapter
import com.example.goingshoppinganimation.constants.ListRespritory
import com.example.goingshoppinganimation.utils.BasicNotification
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val NOTIFICATION_ID = 1

        const val CONSTANT_STRING = "CLICKS"
        const val CONSTANT_STRING2 = "SHOPPING LIST CREATED"
        const val ALMOND = "Almond"


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // TODO call the createView from List Respritory
        ListRespritory.createView()

        button_add.setOnClickListener {
            if(button_add.isPressed)

            //TODO call the notification heer
            BasicNotification.BasicNotification(this)

            var share =  Intent(Intent.ACTION_SEND)
            share.setType("text/plain")
            share.putExtra(Intent.EXTRA_TEXT, "Please make my shopping list ${getSelected()}")
            startActivity(Intent.createChooser(share, "Share link"))

            val favs = getSelected()
            val i = 0

        }
        //TODO instead of setting values here is another short cut

        /*  val manager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
              val adapter = ShoppingListAdapter(createList)
              recycle.layoutManager = manager
              recycle.adapter = adapter */

        recycle.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = ShoppingListAdapter(ListRespritory.createList)
        }
        // TODO trying to get the selected items


    }
    fun getSelected(): String {
        var getSelectedItems = ""
        for (shoppingItems in ListRespritory.createList){
            if(shoppingItems.isChecked) getSelectedItems += "${shoppingItems.name}"
        }
        return getSelectedItems
    }
}
