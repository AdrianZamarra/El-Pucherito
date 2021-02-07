package com.ttt.elpucherito.db.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ttt.elpucherito.db.entity.ShoppingCart
import com.ttt.elpucherito.db.entity.User
import com.ttt.elpucherito.db.relations.ShoppingCartWithDishes

@Dao
interface ShoppingCartDao {

    @Query("SELECT * FROM shopping_carts ORDER BY shopping_cart_id ASC")
    fun getShoppingCarts(): List<ShoppingCart>

    @Query("SELECT * FROM shopping_carts")
    fun getShoppingCartWithDishes(): List<ShoppingCartWithDishes>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertShoppingCarts(shopping_cart: ShoppingCart)

    @Query("DELETE FROM users")
    suspend fun deleteAll()


}
