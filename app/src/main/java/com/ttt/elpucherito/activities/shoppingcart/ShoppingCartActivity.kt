package com.ttt.elpucherito.activities.shoppingcart

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ttt.elpucherito.R
import com.ttt.elpucherito.activities.restaurant.DishItem
import com.ttt.elpucherito.activities.restaurants.RestaurantsActivity
import com.ttt.elpucherito.db.ElPucheritoDB
import com.ttt.elpucherito.db.entities.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList
import kotlin.coroutines.CoroutineContext

class ShoppingCartActivity : AppCompatActivity(), CoroutineScope{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shoppingcart)
        val dishItems : ArrayList<DishItem> = ArrayList()
        val btnBack : ImageView = findViewById(R.id.shoppingcart_btn_back)
        Thread{
            val db: ElPucheritoDB = ElPucheritoDB.getInstance(this)
            val shoppingCart = db.shoppingCartDao().getActiveShoppingCartFromUserID(db.userDao().getLoggedUser().user_id!!)
            val dishesShoppingCarts = db.dishShoppingCartDao().getDishesWithShoppingCartID(shoppingCart.shopping_cart_id!!)
            val dishesList = db.dishDao().getDishes()

            dishesShoppingCarts.forEach {
                val id = it.dish_id
                val quantity = it.quantity
                dishesList.forEach {
                    if (it.dish_id == id){
                        dishItems.add(DishItem(id, it.name, it.description, it.price.toString(), it.restaurant_id, quantity))
                    }
                }
            }
        }.start()
        val btnPurchase: Button = findViewById(R.id.button3)
        btnPurchase.setOnClickListener { finishPurchase() }

        val tvTotal : TextView= findViewById(R.id.tv_precio)

        tvTotal.text = getTotalPrice()

        val recyclerView : RecyclerView = findViewById(R.id.shoppingcart_rv_dish)
        recyclerView.adapter = ShoppingCartAdapter(dishItems, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        btnBack.setOnClickListener{
            val intent = Intent(this, RestaurantsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getTotalPrice() : String {

        var   totalPrice = 0f
        Thread {
            val db: ElPucheritoDB = ElPucheritoDB.getInstance(this)
            val user: User = db.userDao().getLoggedUser()
            val shoppingCart: ShoppingCart = db.shoppingCartDao().getActiveShoppingCartFromUserID(user.user_id!!)
            val dishesShoppingCarts: List<DishShoppingCartRef> = db.dishShoppingCartDao().getDishesWithShoppingCartID(shoppingCart.shopping_cart_id!!)
            val dishesList = db.dishDao().getDishes()
            dishesShoppingCarts.forEach {
                val dishId = it.dish_id
                val quantity = it.quantity
                dishesList.forEach {
                    if (dishId == it.dish_id){
                        totalPrice += it.price * quantity
                    }
                }
            }
        }.start()

        Thread.sleep(80)
        return totalPrice.toString() + "€"
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private fun finishPurchase(){
        Thread {
            val db: ElPucheritoDB = ElPucheritoDB.getInstance(this)
            val user: User = db.userDao().getLoggedUser()
            val shoppingCart: ShoppingCart = db.shoppingCartDao().getActiveShoppingCartFromUserID(user.user_id!!)
            val dishesShoppingCarts = db.dishShoppingCartDao().getDishesWithShoppingCartID(shoppingCart.shopping_cart_id!!)

            var quantity = 0
            dishesShoppingCarts.forEach{
                quantity += it.quantity
            }

            shoppingCart.purchase_date =  Date(Calendar.getInstance().timeInMillis)
            if(dishesShoppingCarts.isEmpty()){
                return@Thread
            }
            shoppingCart.status = 0;
            db.shoppingCartDao().updateShoppingCart(shoppingCart)
            launch{
                db.shoppingCartDao().insertShoppingCart(ShoppingCart(null,null,1, user.user_id))
            }

            val intent = Intent(this, CheckoutActivity::class.java)
            intent.putExtra("quantity", quantity)
            startActivity(intent)
        }.start()
    }

    override fun onBackPressed() {
        startActivity(Intent(this,RestaurantsActivity::class.java))
    }

    override fun onPause() {
        super.onPause()
        finish()
    }

}
