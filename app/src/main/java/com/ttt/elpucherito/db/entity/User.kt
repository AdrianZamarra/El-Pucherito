package com.ttt.elpucherito.db.entity

import androidx.room.*



@Entity(tableName = "users")
class User(
    @PrimaryKey(autoGenerate = true) val user_id: Int,

    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "surname") val surname: String,
    @ColumnInfo(name = "address") val address: String,
    @ColumnInfo(name = "phonenum") val phonenum: Int,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "password") val password: String

)
@Dao
interface UserDao {

    @Query("SELECT * FROM users ORDER BY user_id ASC")
    fun getAlphabetizedWords(): List<User>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    @Query("DELETE FROM users")
    suspend fun deleteAll()
}
