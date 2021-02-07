package com.ttt.elpucherito.db.entity

import android.content.Context
import android.util.Log
import androidx.room.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ttt.elpucherito.util.getJsonDataFromAsset

@Entity(tableName = "assessments",indices = (arrayOf(Index( value = ["user_email"], unique = true))))
data class Assessment(

    @PrimaryKey(autoGenerate = true) val assessments_id: Int?,

    @ColumnInfo(name = "user_email") val user_email: String,
    @ColumnInfo(name = "rating") val rating: Float,
    @ColumnInfo(name = "restaurant_id") val restaurant_id: Int
)
