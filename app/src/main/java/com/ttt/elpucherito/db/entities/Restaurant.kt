package com.ttt.elpucherito.db.entities


import androidx.room.*


@Entity(tableName = "restaurants")
data class Restaurant(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "restaurant_id", index = true)
    val restaurant_id: Int?,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "address") val address: String,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "image") val image : String

    )

