package com.ttt.elpucherito.db.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.ttt.elpucherito.db.entities.Dish
import com.ttt.elpucherito.db.entities.DishShoppingCartRef
import com.ttt.elpucherito.db.entities.ShoppingCart

data class ShoppingCartWithDishes(
    @Embedded val shoppingCart: ShoppingCart,

    @Relation(
        parentColumn = "shopping_cart_id",
        entityColumn = "dish_id",
        associateBy = Junction(DishShoppingCartRef::class)
    )
    val dishes: List<Dish>
)