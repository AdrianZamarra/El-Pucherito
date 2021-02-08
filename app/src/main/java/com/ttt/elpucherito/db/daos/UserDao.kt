package com.ttt.elpucherito.db.daos

import androidx.room.*
import com.ttt.elpucherito.db.entities.User

@Dao
interface UserDao {

    @Query("SELECT * FROM users ORDER BY user_id ASC")
    fun getUsers(): List<User>

    @Query("SELECT * FROM users Where logged = 1")
    fun getLoggedUser(): User

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: User)

    @Update
    fun updateUser(user:User)

    @Query("DELETE FROM users")
    suspend fun deleteAll()

    @Query("SELECT * FROM users WHERE email LIKE :email AND password LIKE :password")
    fun getValidateUser(email : String , password : String): User
}
