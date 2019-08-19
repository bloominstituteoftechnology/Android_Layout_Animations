package com.lambdaschool.sprint2_challenge.listOfItem

import com.lambdaschool.sprint2_challenge.listOfItem.ShoppingItemConstants.ICON_IDS
import com.lambdaschool.sprint2_challenge.listOfItem.ShoppingItemConstants.ITEM_NAMES_RAW
import com.lambdaschool.sprint2_challenge.model.ShoppingListModel

class ShoppingList {
    companion object{
        val List = mutableListOf<ShoppingListModel>()
        fun createShoppingList() {
            for (i in 0 until ICON_IDS.size)
                List.add(ShoppingListModel(ICON_IDS[i], ITEM_NAMES_RAW[i], false))
        }
    }
}