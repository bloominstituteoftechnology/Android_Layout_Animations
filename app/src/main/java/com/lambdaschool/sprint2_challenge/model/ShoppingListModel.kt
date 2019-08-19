package com.lambdaschool.sprint2_challenge.model

import java.io.Serializable

data class ShoppingListModel (val icon: Int,
                              val name: String,
                              var choose: Boolean): Serializable