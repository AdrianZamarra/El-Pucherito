package com.ttt.elpucherito.db.dao

import androidx.room.*
import com.ttt.elpucherito.db.entity.Restaurant
import com.ttt.elpucherito.db.relations.RestaurantWithAssessments
import com.ttt.elpucherito.db.relations.RestaurantWithDishes

@Dao
interface RestaurantDao {

    @Query("SELECT * FROM restaurants ORDER BY restaurant_id ASC")
    fun getRestaurants(): List<Restaurant>

    @Query("SELECT * FROM restaurants WHERE category=:category")
    fun getRestaurantsByCategory(category: String): List<Restaurant>

    @Transaction
    @Query("SELECT * FROM restaurants")
    fun getDishes(): List<RestaurantWithDishes>

    @Transaction
    @Query("SELECT * FROM restaurants")
    fun getAssessments(): List<RestaurantWithAssessments>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(restaurant: Restaurant)

    @Query("DELETE FROM restaurants")
    suspend fun deleteAll()
}