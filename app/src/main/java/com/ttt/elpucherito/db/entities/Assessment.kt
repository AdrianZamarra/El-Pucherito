package com.ttt.elpucherito.db.entities

import androidx.room.*

@Entity(tableName = "assessments")
data class Assessment(

    @PrimaryKey(autoGenerate = true) val assessments_id: Int?,

    @ColumnInfo(name = "user_email") val user_email: String,
    @ColumnInfo(name = "rating") var rating: Float,
    @ColumnInfo(name = "restaurant_id") val restaurant_id: Int
)
