package com.lambdaschool.sprint2_challenge.Shopping_items

import com.lambdaschool.sprint2_challenge.Model.Product

class GroceryRepo {
    companion object {
        val GroceryList = mutableListOf<Product>()
        fun createGroceryList() {

            for (i in 0 until GroceryNames.size ) {
                GroceryList.add(Product(GroceryNames[i],false, groceryIds[i] ))
            }
        }
    }
}