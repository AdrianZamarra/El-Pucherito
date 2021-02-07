package com.ttt.elpucherito.db.entity

import androidx.room.*

@Dao
interface DishDao {

    @Query("SELECT * FROM dishes ORDER BY dish_id ASC")
    fun getDishes(): List<Dish>



    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDish(dish: Dish)

    @Query("DELETE FROM dishes")
    suspend fun deleteAll()
}
