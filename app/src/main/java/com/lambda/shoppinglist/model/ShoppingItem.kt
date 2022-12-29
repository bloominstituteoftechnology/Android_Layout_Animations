package com.lambda.shoppinglist.model

import java.io.Serializable

class ShoppingItem(itemName:String,itemImageID:Int,itemIndex:Int):Serializable{
    val itemName:String=itemName
    val itemImageId:Int=itemImageID
    val itemIndex:Int=itemIndex

}