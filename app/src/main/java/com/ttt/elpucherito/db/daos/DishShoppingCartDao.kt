package com.ttt.elpucherito.db.daos

import androidx.room.*
import com.ttt.elpucherito.db.entities.Dish
import com.ttt.elpucherito.db.entities.DishShoppingCartRef
import com.ttt.elpucherito.db.entities.ShoppingCart
import com.ttt.elpucherito.db.relations.ShoppingCartWithDishes

@Dao
interface DishShoppingCartDao {
    @Transaction
    @Query("SELECT * FROM dishes_shopping_carts")
    fun getShoppingCarts(): List<DishShoppingCartRef>

    @Transaction
    @Query("SELECT * FROM dishes_shopping_carts WHERE shopping_cart_id = :ShoppingCartID")
    fun getDishesWithShoppingCartID(ShoppingCartID:Int): List<DishShoppingCartRef>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDishesShoppingCarts(dishShoppingCartRef: DishShoppingCartRef)
    @Update
    fun updateDishesShoppingCarts(dishShoppingCartRef: DishShoppingCartRef)

    @Query("DELETE FROM dishes_shopping_carts")
    suspend fun deleteAll()

    @Query("DELETE FROM dishes_shopping_carts where dish_id=:dishID")
    fun deleteWithDishID(dishID:Int)
}
