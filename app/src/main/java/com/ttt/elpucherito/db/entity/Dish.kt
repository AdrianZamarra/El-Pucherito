package com.ttt.elpucherito.db.entity

import android.content.Context
import android.util.Log
import androidx.room.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ttt.elpucherito.util.getJsonDataFromAsset

@Entity(tableName = "dishes")
data class Dish(

    @PrimaryKey(autoGenerate = true) val dish_id: Int?,

    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "price") val price: Float,

    @ColumnInfo(name = "restaurant_id") val restaurant_id: Int
    )
