package com.ttt.elpucherito.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ttt.elpucherito.db.entities.Dish
import com.ttt.elpucherito.db.entities.DishesShoppingCarts
import com.ttt.elpucherito.db.entities.ShoppingCart
import com.ttt.elpucherito.db.relations.ShoppingCartWithDishes

@Dao
interface DishesShoppingCartsDao {



    @Query("SELECT * FROM dishes_shopping_carts WHERE shopping_cart_id = :ShoppingCartID")
    fun getDishesWithShoppingCartID(ShoppingCartID:Int): List<DishesShoppingCartsDao>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDishesShoppingCarts(dishesShoppingCarts: DishesShoppingCarts)

    @Query("DELETE FROM users")
    suspend fun deleteAll()


}
