package com.ttt.elpucherito.activities.shoppingcart

import android.content.Context
import android.media.MediaPlayer
import android.content.Intent

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ttt.elpucherito.R
import com.ttt.elpucherito.activities.restaurant.DishItem
import com.ttt.elpucherito.activities.restaurants.RestaurantAdapter
import com.ttt.elpucherito.activities.restaurants.RestaurantsActivity
import com.ttt.elpucherito.db.ElPucheritoDB
import com.ttt.elpucherito.db.entities.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ShoppingCartActivity : AppCompatActivity(), CoroutineScope{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shoppingcart)
        var dishItems : ArrayList<DishItem> = ArrayList()
        Thread{
            var db: ElPucheritoDB = ElPucheritoDB.getInstance(this)
            var shoppingCart = db.shoppingCartDao().getActiveShoppingCartFromUserID(db.userDao().getLoggedUser().user_id!!)
            var dishesShoppingCarts = db.dishShoppingCartDao().getDishesWithShoppingCartID(shoppingCart.shopping_cart_id!!)
            val dishesList = db.dishDao().getDishes()

            dishesShoppingCarts.forEach {
                val id = it.dish_id
                val quantity = it.quantity
                println("Plato con id ${it.dish_id} con cantidad: ${it.quantity}")
                dishesList.forEach {
                    if (it.dish_id == id){
                        dishItems.add(DishItem(id, it.name, it.description, it.price.toString(), it.restaurant_id, quantity))
                    }
                }
            }
        }.start()
        val btnPurchase: Button = findViewById(R.id.button3)
        btnPurchase.setOnClickListener { finishPurchase() }


        val recyclerView : RecyclerView = findViewById(R.id.shoppingcart_rv_dish)
        recyclerView.adapter = ShoppingCartAdapter(dishItems, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private fun finishPurchase(){
        val mediaPlayer = MediaPlayer.create(this, R.raw.confirm_purchase)
        mediaPlayer.start()
        Thread {
            var db: ElPucheritoDB = ElPucheritoDB.getInstance(this)

            var user: User = db.userDao().getLoggedUser()
            var shoppingCart: ShoppingCart = db.shoppingCartDao().getActiveShoppingCartFromUserID(user.user_id!!)
            var dishesShoppingCarts = db.dishShoppingCartDao().getDishesWithShoppingCartID(shoppingCart.shopping_cart_id!!)
            if(dishesShoppingCarts.isEmpty()){
                return@Thread
            }
        shoppingCart.status = 0;
            db.shoppingCartDao().updateShoppingCart(shoppingCart)

            launch{

        }

            val intent = Intent(this, CheckoutActivity::class.java)
            startActivity(intent)
    }.start()

    }
}
