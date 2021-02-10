package com.ttt.elpucherito.db.entities

import androidx.room.*
import java.util.*


@Entity(tableName = "shopping_carts")
data class ShoppingCart(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "shopping_cart_id", index = true)
    val shopping_cart_id: Int?,
    @ColumnInfo(name = "purchase_date") var parchase_date: Date?,
    @ColumnInfo(name = "status") var status: Int,
    @ColumnInfo(name = "user_id") val user_id: Int
)
