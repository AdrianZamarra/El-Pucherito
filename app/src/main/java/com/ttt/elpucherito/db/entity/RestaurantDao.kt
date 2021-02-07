package com.ttt.elpucherito.db.entity

import androidx.room.*
import com.ttt.elpucherito.db.relations.RestaurantWithDishes

@Dao
interface RestaurantDao {

    @Query("SELECT * FROM restaurants ORDER BY restaurant_id ASC")
    fun getRestaurants(): List<Restaurant>

    @Transaction
    @Query("SELECT * FROM restaurants")
    fun getDishes(): List<RestaurantWithDishes>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(restaurant: Restaurant)

    @Query("DELETE FROM restaurants")
    suspend fun deleteAll()
}