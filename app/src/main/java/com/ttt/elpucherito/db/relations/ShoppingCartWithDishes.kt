package com.ttt.elpucherito.db.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.ttt.elpucherito.db.entity.Dish
import com.ttt.elpucherito.db.entity.DishesShoppingCarts
import com.ttt.elpucherito.db.entity.ShoppingCart

data class ShoppingCartWithDishes(
    @Embedded val shoppingCart: ShoppingCart,

    @Relation(
        parentColumn = "shopping_cart_id",
        entityColumn = "dish_id",
        associateBy = Junction(DishesShoppingCarts::class)
    )
    val dishes: List<Dish>
)