package com.ttt.elpucherito.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dishes_shopping_carts",primaryKeys = ["dish_id", "shopping_cart_id"])
data class DishesShoppingCarts(
    @ColumnInfo(name = "dish_id")val dish_id: Int,
    @ColumnInfo(name = "shopping_cart_id")val shopping_cart_id: Int
)
