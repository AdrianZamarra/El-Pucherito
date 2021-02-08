package com.ttt.elpucherito.db.entities

import androidx.room.*
import java.util.*


@Entity(tableName = "shopping_carts")
data class ShoppingCart(

    @PrimaryKey(autoGenerate = true) val shopping_cart_id: Int?,

    @ColumnInfo(name = "purchase_date") val parchase_date: Date,
    @ColumnInfo(name = "status") val status: Int,
    @ColumnInfo(name = "user_id") val user_id: Int
)
