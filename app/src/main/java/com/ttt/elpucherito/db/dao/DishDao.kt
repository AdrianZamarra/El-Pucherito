package com.ttt.elpucherito.db.dao

import androidx.room.*
import com.ttt.elpucherito.db.entity.Dish

@Dao
interface DishDao {

    @Query("SELECT * FROM dishes ORDER BY dish_id ASC")
    fun getDishes(): List<Dish>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDish(dish: Dish)

    @Query("DELETE FROM dishes")
    suspend fun deleteAll()
}
