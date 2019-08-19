package com.lambda.shoppinglist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Explode
import android.view.Window
import androidx.core.content.ContextCompat
import com.lambda.shoppinglist.R
import com.lambda.shoppinglist.model.ShoppingItem
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        window.enterTransition = Explode()
        window.exitTransition = Explode()

        setContentView(R.layout.activity_details)

        val item = intent.getSerializableExtra("ITEM_KEY") as ShoppingItem
        item_name.text=item.itemName
        item_image.setImageDrawable(ContextCompat.getDrawable(this, item.itemImageId))
    }
}
