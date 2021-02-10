package com.ttt.elpucherito.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity( tableName = "dishes_shopping_carts",primaryKeys = ["dish_id", "shopping_cart_id"])
data class DishShoppingCartRef(

    @ColumnInfo(name = "dish_id", index = true)
    val dish_id: Int,
    @ColumnInfo(name = "shopping_cart_id", index = true)
    val shopping_cart_id: Int,
    @ColumnInfo(name = "quantity") var quantity: Int

)