package com.ttt.elpucherito.activities.shoppingcart

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ttt.elpucherito.R
import com.ttt.elpucherito.db.ElPucheritoDB
import com.ttt.elpucherito.db.entities.Dish
import com.ttt.elpucherito.db.entities.DishesShoppingCarts
import com.ttt.elpucherito.db.entities.ShoppingCart
import com.ttt.elpucherito.db.entities.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ShoppingCartActivity : AppCompatActivity(), CoroutineScope{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shoppingcart)

        Thread{
            var db: ElPucheritoDB = ElPucheritoDB.getInstance(this)

            var shoppingCart = db.shoppingCartDao().getActiveShoppingCartFromUserID(db.userDao().getLoggedUser().user_id!!)

            var dishesShoppingCarts = db.dishesShoppingCartsDao().getDishesWithShoppingCartID(shoppingCart.shopping_cart_id!!)

            dishesShoppingCarts.forEach {
                println(it)
            }
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

fun addDishToShoppingCart(context: Context, dish: Dish){

    Thread {
        var db: ElPucheritoDB = ElPucheritoDB.getInstance(this)

        var user: User = db.userDao().getLoggedUser()
        val shoppingCart: ShoppingCart = db.shoppingCartDao().getActiveShoppingCartFromUserID(user.user_id!!)
    launch{

        if (shoppingCart == null) {
            db.shoppingCartDao().insertShoppingCart(ShoppingCart(null,null,1,user.user_id!!))

        } else {

            db.dishesShoppingCartsDao().insertDishesShoppingCarts(DishesShoppingCarts(dish.dish_id!!,shoppingCart.shopping_cart_id!!))

        }
    }
    }.start()

}

fun finishPurchase(context:Context){
    Thread {
        var db: ElPucheritoDB = ElPucheritoDB.getInstance(this)

        var user: User = db.userDao().getLoggedUser()
        var shoppingCart: ShoppingCart = db.shoppingCartDao().getActiveShoppingCartFromUserID(user.user_id!!)
        shoppingCart.status = 0;
        db.shoppingCartDao().updateShoppingCart(shoppingCart)
        //_INTENT AQUI ABAJO

    }.start()

}
//Para consultar los platos de un carrito



}
