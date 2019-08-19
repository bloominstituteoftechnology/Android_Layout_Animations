package com.lambdaschool.sprint2_challenge.Shopping_items

import android.app.SharedElementCallback
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.transition.Explode
import android.view.Window
import com.lambdaschool.sprint2_challenge.Model.Product
import com.lambdaschool.sprint2_challenge.R
import kotlinx.android.synthetic.main.activity_item_detail.*

class itemDetail : AppCompatActivity() {

    companion object {

       const val ITEM_KEY = "SHOPPING_ITEM"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        window.enterTransition = Explode()
        window.exitTransition = Explode()

        setContentView(R.layout.activity_item_detail)

       val item = intent.getSerializableExtra(ITEM_KEY) as Product

        item_name.text = item.title
        item_image.setImageDrawable(ContextCompat.getDrawable(this,item.imageId))
    }
}
