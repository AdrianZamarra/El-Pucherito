package com.ttt.elpucherito.db.entity


import androidx.room.*



@Entity(tableName = "restaurants")
class Restaurant(
    @PrimaryKey(autoGenerate = true) val restaurant_id: Int,

    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "address") val address: String,
    @ColumnInfo(name = "assessment") val assessment: Float


)
@Dao
interface RestaurantDao {

    @Query("SELECT * FROM restaurants ORDER BY restaurant_id ASC")
    fun getAlphabetizedWords(): List<Restaurant>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(restaurant: Restaurant)

    @Query("DELETE FROM restaurants")
    suspend fun deleteAll()
}

