package com.lambdaschool.sprint2_challenge.listOfItem

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.transition.Explode
import android.transition.Slide
import android.view.Window
import com.lambdaschool.sprint2_challenge.R
import com.lambdaschool.sprint2_challenge.model.ShoppingListModel
import kotlinx.android.synthetic.main.shopping_item_detail.*

class ItemDetail : AppCompatActivity() {

    companion object {
        const val ITEM_KEY = "SHOPPING_ITEM"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        window.enterTransition = Explode()
        window.exitTransition = Explode()

        setContentView(R.layout.shopping_item_detail)

        val item = intent.getSerializableExtra(ITEM_KEY) as ShoppingListModel

        item_name.text = item.name
        item_image.setImageDrawable(ContextCompat.getDrawable(this, item.icon))
    }
}
