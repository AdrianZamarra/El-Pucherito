package com.ttt.elpucherito.db.entity

import androidx.room.Entity

@Entity(primaryKeys = ["dish_id", "shopping_cart_id"])
data class DishesShoppingCarts(
    val dish_id: Int,
    val shopping_cart_id: Int
)
