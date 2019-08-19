package com.example.goingshoppinganimation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Explode
import android.transition.Fade
import android.view.Window
import com.example.goingshoppinganimation.R
import com.example.goingshoppinganimation.constants.ShoppingList
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    companion object {
        val ITEM_KEY = "SHOPPING ITEM"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        window.enterTransition = Explode()

        window.exitTransition = Fade()

        setContentView(R.layout.activity_detail)

        val item = intent.getSerializableExtra(ITEM_KEY) as ShoppingList
        test_text.text = item.name
    }
}
