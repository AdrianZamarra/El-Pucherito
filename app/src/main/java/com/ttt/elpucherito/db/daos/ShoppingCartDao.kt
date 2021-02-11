package com.ttt.elpucherito.db.daos


import androidx.room.*
import com.ttt.elpucherito.db.entities.Dish
import com.ttt.elpucherito.db.entities.ShoppingCart
import com.ttt.elpucherito.db.relations.ShoppingCartWithDishes

@Dao
interface ShoppingCartDao {

    @Query("SELECT * FROM shopping_carts ORDER BY shopping_cart_id ASC")
    fun getShoppingCarts(): List<ShoppingCart>
    @Query("SELECT * FROM shopping_carts WHERE  status = 0 ORDER BY purchase_date DESC")
    fun getOrders(): List<ShoppingCart>
    @Transaction
    @Query("SELECT * FROM shopping_carts")
    fun getShoppingCartWithDishes(): List<ShoppingCartWithDishes>

    @Query("SELECT * FROM shopping_carts WHERE status = 1 and user_id=:userID ")
    fun getActiveShoppingCartFromUserID(userID:Int): ShoppingCart

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertShoppingCart(shopping_cart: ShoppingCart)

    @Update
    fun updateShoppingCart(shopping_cart: ShoppingCart)

    @Query("DELETE FROM users")
    suspend fun deleteAll()


}
