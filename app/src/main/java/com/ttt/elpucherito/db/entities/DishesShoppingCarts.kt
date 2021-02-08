package com.ttt.elpucherito.db.entities

import androidx.room.Entity

@Entity(tableName = "dishes_shopping_carts",primaryKeys = ["dish_id", "shopping_cart_id"])
data class DishesShoppingCarts(
    val dish_id: Int,
    val shopping_cart_id: Int
)
