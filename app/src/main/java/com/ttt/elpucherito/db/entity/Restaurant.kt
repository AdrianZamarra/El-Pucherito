package com.ttt.elpucherito.db.entity


import android.content.Context
import android.util.Log
import androidx.room.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ttt.elpucherito.util.getJsonDataFromAsset
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


@Entity(tableName = "restaurants")
data class Restaurant(

    @PrimaryKey(autoGenerate = true) val restaurant_id: Int?,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "address") val address: String,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "image") val image : String,
    @ColumnInfo(name = "assessment") val assessment: Float

    )

