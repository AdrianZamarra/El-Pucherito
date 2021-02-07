package com.ttt.elpucherito.db.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.ttt.elpucherito.db.entity.Dish
import com.ttt.elpucherito.db.entity.DishesShoppingCarts
import com.ttt.elpucherito.db.entity.ShoppingCart

data class DishWithShoppingCarts(
    @Embedded val dish: Dish,
    @Relation(
        parentColumn = "dish_id",
        entityColumn = "shopping_cart_id",
        associateBy = Junction(DishesShoppingCarts::class)
    )
    val songs: List<ShoppingCart>
)