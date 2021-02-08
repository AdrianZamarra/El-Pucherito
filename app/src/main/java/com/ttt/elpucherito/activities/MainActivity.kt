package com.ttt.elpucherito.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ttt.elpucherito.R

import com.ttt.elpucherito.activities.restaurants.RestaurantsActivity
import com.ttt.elpucherito.activities.shoppingcart.ShoppingCartActivity
import com.ttt.elpucherito.activities.users.LoginActivity
import com.ttt.elpucherito.db.ElPucheritoDB
import com.ttt.elpucherito.db.entities.Dish
import com.ttt.elpucherito.db.entities.DishesShoppingCarts
import com.ttt.elpucherito.db.entities.ShoppingCart
import com.ttt.elpucherito.db.entities.User


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Thread {
            var db: ElPucheritoDB = ElPucheritoDB.getInstance(this)
            db.printAllEntities(this)
            var user: User = db.userDao().getLoggedUser()

            if (user != null) {
                var intent = Intent(this, RestaurantsActivity::class.java)
                startActivity(intent)
            } else {
                var intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }.start()
    }
}

/*
    fun addDishToShoppingCart(context:Context,dish:Dish){

        Thread {
            var db: ElPucheritoDB = ElPucheritoDB.getInstance(this)

            var user: User = db.userDao().getLoggedUser()
            val shoppingCart: ShoppingCart = db.shoppingCartDao().getActiveShoppingCartFromUserID(user.user_id!!)
        launch{

            if (shoppingCart == null) {
                db.shoppingCartDao().insertShoppingCarts(ShoppingCart(null,null,1,user.user_id!!))

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
    // db.dishesShoppingCartsDao().getDishesWithShoppingCartID(shoppingCart.shopping_cart_id!!)
/*
