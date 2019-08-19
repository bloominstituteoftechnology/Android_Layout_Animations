package com.example.goingshoppinganimation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.goingshoppinganimation.R

class DetailActivity : AppCompatActivity() {
    companion object {
        val ITEM_KEY = "SHOPPING ITEM"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }
}
