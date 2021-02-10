package com.ttt.elpucherito.activities.shoppingcart

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.ttt.elpucherito.R
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

        Thread{
            var db: ElPucheritoDB = ElPucheritoDB.getInstance(this)

            var shoppingCart = db.shoppingCartDao().getActiveShoppingCartFromUserID(db.userDao().getLoggedUser().user_id!!)
            var dishesShoppingCarts = db.dishShoppingCartDao().getDishesWithShoppingCartID(shoppingCart.shopping_cart_id!!)

            dishesShoppingCarts.forEach {
                println(it)
            }
        }.start()
        val btnPurchase: Button = findViewById(R.id.button3)
        btnPurchase.setOnClickListener(View.OnClickListener { finishPurchase(this) })

    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

private fun finishPurchase(context:Context){
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

             db.shoppingCartDao().insertShoppingCart(ShoppingCart(null,null,1,user.user_id!!))

        }

    }.start()

}


}
