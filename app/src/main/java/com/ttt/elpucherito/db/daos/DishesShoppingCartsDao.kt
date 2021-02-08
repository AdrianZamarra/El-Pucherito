package com.ttt.elpucherito.db.daos

import androidx.room.*
import com.ttt.elpucherito.db.entities.Dish
import com.ttt.elpucherito.db.entities.DishesShoppingCarts
import com.ttt.elpucherito.db.entities.ShoppingCart
import com.ttt.elpucherito.db.relations.ShoppingCartWithDishes

@Dao
interface DishesShoppingCartsDao {
    @Transaction
    @Query("SELECT * FROM shopping_carts")
    fun getShoppingCarts(): List<ShoppingCartWithDishes>

    @Transaction
    @Query("SELECT * FROM shopping_carts WHERE shopping_cart_id = :ShoppingCartID")
    fun getDishesWithShoppingCartID(ShoppingCartID:Int): List<ShoppingCartWithDishes>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDishesShoppingCarts(dishesShoppingCarts: DishesShoppingCarts)



}
