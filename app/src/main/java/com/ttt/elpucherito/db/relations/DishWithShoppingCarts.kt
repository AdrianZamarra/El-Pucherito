package com.ttt.elpucherito.db.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.ttt.elpucherito.db.entities.Dish
import com.ttt.elpucherito.db.entities.DishShoppingCartRef
import com.ttt.elpucherito.db.entities.ShoppingCart

data class DishWithShoppingCarts(
    @Embedded val dish: Dish,

    @Relation(
        parentColumn = "dish_id",
        entityColumn = "shopping_cart_id",
        associateBy = Junction(DishShoppingCartRef::class)
    )
    val shoppingCarts: List<ShoppingCart>
)