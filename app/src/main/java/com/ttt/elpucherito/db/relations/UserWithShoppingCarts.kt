package com.ttt.elpucherito.db.relations


import androidx.room.Embedded
import androidx.room.Relation
import com.ttt.elpucherito.db.entities.ShoppingCart
import com.ttt.elpucherito.db.entities.User


data class UserWithShoppingCarts(
    @Embedded val user: User?,
    @Relation(
        parentColumn = "user_id",
        entityColumn = "user_id"
    )
    val dishes: List<ShoppingCart>
)