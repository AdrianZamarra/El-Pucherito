package com.ttt.elpucherito.db.entity

import android.content.Context
import android.util.Log
import androidx.room.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*


@Entity(tableName = "shopping_carts")
data class ShoppingCart(

    @PrimaryKey(autoGenerate = true) val shopping_cart_id: Int?,

    @ColumnInfo(name = "purchase_date") val parchase_date: Date,
    @ColumnInfo(name = "status") val status: Int,
    @ColumnInfo(name = "user_id") val user_id: Int
)
