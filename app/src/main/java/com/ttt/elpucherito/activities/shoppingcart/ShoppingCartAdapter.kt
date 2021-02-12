package com.ttt.elpucherito.activities.shoppingcart

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ttt.elpucherito.R
import com.ttt.elpucherito.activities.restaurant.DishItem
import com.ttt.elpucherito.db.ElPucheritoDB
import com.ttt.elpucherito.db.entities.DishShoppingCartRef

class ShoppingCartAdapter(private val dishesList : List<DishItem>, private val context: Context) : RecyclerView.Adapter<ShoppingCartAdapter.ChartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChartViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.shoppingcart_item, parent, false)
        return ChartViewHolder(itemView)
    }

    override fun getItemCount() = dishesList.size

    override fun onBindViewHolder(holder: ChartViewHolder, position: Int) {
        val currentItem = dishesList[position]

        val quantity = currentItem.price.toFloat() * currentItem.quantity!!

        holder.title.text = currentItem.title
        holder.quantity.text = currentItem.quantity.toString()
        holder.buy.text = quantity.toString() + "€"


        holder.bind(currentItem, context)
    }

    class ChartViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val title : TextView = itemView.findViewById(R.id.shoppingcart_item_name)
        val quantityAddButton : Button = itemView.findViewById(R.id.shoppingcart_btn_add)
        val quantity : TextView = itemView.findViewById(R.id.shoppingcart_item_quantity)
        val quantitySubstractButton : Button = itemView.findViewById(R.id.shoppingcart_btn_substract)
        val buy : TextView = itemView.findViewById(R.id.shoppingcart_item_price)
        val removeDishButton : Button = itemView.findViewById(R.id.shoppingcart_item_delete)

        // BOTON PARA QUITAR DEL CARRITO
        val deleteItemButton : ImageView = itemView.findViewById(R.id.shoppingcart_item_delete)


        fun bind(dishItem : DishItem, context: Context) {

            quantityAddButton.setOnClickListener {
                val newQuantity = Integer.parseInt(quantity.text.toString()) + 1
                quantity.text = newQuantity.toString()

                val price = dishItem.price.toFloat() * quantity.text.toString().toInt()
                buy.text = price.toString() + "€"

                Thread{
                    val db = ElPucheritoDB.getInstance(context)
                    val activeUser = db.userDao().getLoggedUser()
                    val shoppingCart = db.shoppingCartDao().getActiveShoppingCartFromUserID(activeUser.user_id!!)
                    val dishesShoppingCarts: List<DishShoppingCartRef> = db.dishShoppingCartDao().getDishesWithShoppingCartID(shoppingCart.shopping_cart_id!!)
                    dishesShoppingCarts.forEach{
                        if(it.dish_id == dishItem.dish_id ){
                            it.quantity++
                            db.dishShoppingCartDao().updateDishesShoppingCarts(it)
                            return@Thread
                        }
                    }
                }.start()

                context as Activity
                val tvTotal : TextView= context.findViewById(R.id.tv_precio)
                tvTotal.text = (tvTotal.text.toString().toFloat() + dishItem.price.toFloat()).toString()

            }


            quantitySubstractButton.setOnClickListener {
                if (Integer.parseInt(quantity.text.toString()) > 1){
                    val newQuantity = Integer.parseInt(quantity.text.toString()) - 1
                    quantity.text = newQuantity.toString()

                    val price = dishItem.price.toFloat() * quantity.text.toString().toInt()
                    buy.text = price.toString() + "€"
                    Thread{
                        val db = ElPucheritoDB.getInstance(context)
                        val activeUser = db.userDao().getLoggedUser()
                        val shoppingCart = db.shoppingCartDao().getActiveShoppingCartFromUserID(activeUser.user_id!!)
                        val dishesShoppingCarts: List<DishShoppingCartRef> = db.dishShoppingCartDao().getDishesWithShoppingCartID(shoppingCart.shopping_cart_id!!)
                        dishesShoppingCarts.forEach{
                            if(it.dish_id == dishItem.dish_id ){
                                it.quantity--
                                db.dishShoppingCartDao().updateDishesShoppingCarts(it)
                                return@Thread
                            }
                        }
                    }.start()
                    //context.startActivity(Intent(context, ShoppingCartActivity::class.java))
                    context as Activity
                    val tvTotal : TextView= context.findViewById(R.id.tv_precio)
                    tvTotal.text = (tvTotal.text.toString().toFloat() - dishItem.price.toFloat()).toString()
                }
            }
            deleteItemButton.setOnClickListener(View.OnClickListener {
                Thread {
                    val db = ElPucheritoDB.getInstance(context)
                    val activeUser = db.userDao().getLoggedUser()
                   // db.dishShoppingCartDao().getDishesWithShoppingCartID()

                }.start()



            })

        }
    }
}