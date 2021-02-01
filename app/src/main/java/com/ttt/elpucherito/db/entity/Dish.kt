package com.ttt.elpucherito.db.entity

import androidx.room.*

@Entity(tableName = "dishes")
class Dish(
    @PrimaryKey(autoGenerate = true) val dish_id: Int,

    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "price") val price: Float,
    @Embedded val restaurant: Restaurant

    )
@Dao
interface DishDao {

    @Query("SELECT * FROM dishes ORDER BY dish_id ASC")
    fun getAlphabetizedWords(): List<Dish>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(dish: Dish)

    @Query("DELETE FROM dishes")
    suspend fun deleteAll()
}
