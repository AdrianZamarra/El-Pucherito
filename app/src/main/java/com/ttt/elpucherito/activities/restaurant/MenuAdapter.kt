package com.ttt.elpucherito.activities.restaurant

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.ttt.elpucherito.R
import com.ttt.elpucherito.activities.shoppingcart.ShoppingCartActivity
import com.ttt.elpucherito.db.ElPucheritoDB
import com.ttt.elpucherito.db.entities.DishShoppingCartRef
import com.ttt.elpucherito.db.entities.ShoppingCart
import com.ttt.elpucherito.db.entities.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/**
 * DishesAdapter
 */
class MenuAdapter(private val dishesList: List<DishItem>, private val context: Context) :
    RecyclerView.Adapter<MenuAdapter.ChartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChartViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.restaurant_chart_dish, parent, false)

        return ChartViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ChartViewHolder, position: Int) {
        val currentItem = dishesList[position]
        holder.title.text = currentItem.title
        holder.description.text = currentItem.description
        holder.price.text = currentItem.price + "€"
        holder.bind(currentItem, context)
    }

    override fun getItemCount() = dishesList.size

    /**
     * Internal class
     */
    class ChartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), CoroutineScope {
        val title: TextView = itemView.findViewById(R.id.restaurant_dish_name)
        val description: TextView = itemView.findViewById(R.id.restaurant_dish_description)
        val buy: ImageView = itemView.findViewById(R.id.restaurant_dish_buy)
        val price: TextView = itemView.findViewById(R.id.restaurant_dish_price)

        fun bind(dishItem: DishItem, context: Context) {
            buy.setOnClickListener {

                val builder = AlertDialog.Builder(context)
                with(builder) {
                    setCancelable(true)
                    setTitle(context.getString(R.string.product_added))
                    setMessage(context.getString(R.string.product_added_information))
                    setPositiveButton(context.getString(R.string.keep_buying),
                        DialogInterface.OnClickListener { dialog, which ->
                        })
                    setNegativeButton(
                        context.getString(R.string.go_shopping_cart),
                        DialogInterface.OnClickListener { dialogInterface: DialogInterface, i: Int ->
                            var shoppingCartIntent =
                                Intent(context, ShoppingCartActivity::class.java)
                            context.startActivity(shoppingCartIntent)
                        }
                    )
                    setIcon(android.R.drawable.checkbox_on_background)
                    show()
                }
                addDishToShoppingCart(context, dishItem.dish_id)
            }
        }

        override val coroutineContext: CoroutineContext
            get() = Dispatchers.Main
        /**
         * @param context
         * @param dish_id, id of the dish to add
         */
        private fun addDishToShoppingCart(context: Context, dish_id: Int) {

            Thread {
                var db: ElPucheritoDB = ElPucheritoDB.getInstance(context)
                var user: User = db.userDao().getLoggedUser()
                val shoppingCart: ShoppingCart =  db.shoppingCartDao().getActiveShoppingCartFromUserID(user.user_id!!)
                val dishesShoppingCarts: List<DishShoppingCartRef> = db.dishShoppingCartDao().getDishesWithShoppingCartID(shoppingCart.shopping_cart_id!!)
                dishesShoppingCarts.forEach {
                    if (it.dish_id == dish_id) {
                        it.quantity++
                        db.dishShoppingCartDao().updateDishesShoppingCarts(it)
                        return@Thread
                    }
                }
                launch {

                    if (shoppingCart != null) {

                        db.dishShoppingCartDao().insertDishesShoppingCarts(
                            DishShoppingCartRef(
                                dish_id,
                                shoppingCart.shopping_cart_id!!,
                                1
                            )
                        )
                    }
                }
            }.start()
        }
    }
}