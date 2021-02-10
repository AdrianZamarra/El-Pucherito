package com.ttt.elpucherito.db.entities

import androidx.room.*

@Entity(tableName = "dishes")
data class Dish(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "dish_id", index = true)
    val dish_id: Int?,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "price") val price: Float,
    @ColumnInfo(name = "restaurant_id") val restaurant_id: Int
)
