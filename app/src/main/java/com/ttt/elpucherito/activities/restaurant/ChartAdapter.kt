package com.ttt.elpucherito.activities.restaurant

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ttt.elpucherito.R
import com.ttt.elpucherito.db.ElPucheritoDB
import com.ttt.elpucherito.db.entities.Dish
import com.ttt.elpucherito.db.entities.DishShoppingCartRef
import com.ttt.elpucherito.db.entities.ShoppingCart
import com.ttt.elpucherito.db.entities.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ChartAdapter(private val dishesList : List<DishItem>, private val context: Context) : RecyclerView.Adapter<ChartAdapter.ChartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChartViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.restaurant_chart_dish, parent, false)

        return ChartViewHolder(itemView)
    }

    override fun getItemCount() = dishesList.size

    override fun onBindViewHolder(holder: ChartViewHolder, position: Int) {
        val currentItem = dishesList[position]

        holder.title.text = currentItem.title
        holder.description.text = currentItem.description
        holder.buy.text = currentItem.price
        holder.bind(currentItem, context)
    }

    class ChartViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView), CoroutineScope {
        val title : TextView = itemView.findViewById(R.id.restaurant_dish_name)
        val description : TextView = itemView.findViewById(R.id.restaurant_dish_description)
        val buy : Button = itemView.findViewById(R.id.restaurant_dish_buy)

        fun bind(dishItem : DishItem, context: Context) {
            buy.setOnClickListener {
                Toast.makeText(context, dishItem.title, Toast.LENGTH_SHORT).show()

                addDishToShoppingCart(context,dishItem.dish_id)
            }
        }

        override val coroutineContext: CoroutineContext
            get() = Dispatchers.Main

        private fun addDishToShoppingCart(context: Context, dish_id: Int){

            Thread {
                var db: ElPucheritoDB = ElPucheritoDB.getInstance(context)
                var user: User = db.userDao().getLoggedUser()
                val shoppingCart: ShoppingCart = db.shoppingCartDao().getActiveShoppingCartFromUserID(user.user_id!!)
                val dishesShoppingCarts: List<DishShoppingCartRef> = db.dishShoppingCartDao().getDishesWithShoppingCartID(shoppingCart.shopping_cart_id!!)
                dishesShoppingCarts.forEach{
                    if(it.dish_id == dish_id ){
                        it.quantity++
                        return@Thread
                    }
                }
                launch{

                    if (shoppingCart != null) {

                        db.dishShoppingCartDao().insertDishesShoppingCarts(DishShoppingCartRef(dish_id,shoppingCart.shopping_cart_id!!,1))
                    }

                }

            }.start()
        }
    }
}