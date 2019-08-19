package com.lambdaschool.sprint2_challenge.Model

import android.graphics.drawable.Drawable
import java.io.Serializable

class Product (val title:String, var purchased:Boolean, val imageId: Int) : Serializable {
    val formattedName: String
        get() {
            val re = Regex("[^A-Za-z_ ]")
            return re.replace(this.title, "").replace("_", " ")
        }
}


