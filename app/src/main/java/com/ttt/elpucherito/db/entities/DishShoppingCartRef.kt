package com.ttt.elpucherito.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity( tableName = "dishes_shopping_carts",primaryKeys = ["dish_id", "shopping_cart_id"])
data class DishShoppingCartRef(
    val dish_id: Int,
    val shopping_cart_id: Int

)