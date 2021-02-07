package com.ttt.elpucherito.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ttt.elpucherito.db.entity.User

@Dao
interface UserDao {

    @Query("SELECT * FROM users ORDER BY user_id ASC")
    fun getUsers(): List<User>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: User)

    @Query("DELETE FROM users")
    suspend fun deleteAll()

    @Query("SELECT * FROM users WHERE email LIKE :email AND password LIKE :password")
    fun getValidateUser(email : String , password : String): User
}
