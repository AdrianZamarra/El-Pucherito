package com.ttt.elpucherito.activities.shoppingcart

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ttt.elpucherito.R
import com.ttt.elpucherito.activities.restaurant.DishItem

class ShoppingCartAdapter(private val dishesList : List<DishItem>, private val context: Context) : RecyclerView.Adapter<ShoppingCartAdapter.ChartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChartViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.shoppingcart_item, parent, false)

        return ChartViewHolder(itemView)
    }

    override fun getItemCount() = dishesList.size

    override fun onBindViewHolder(holder: ChartViewHolder, position: Int) {
        val currentItem = dishesList[position]

        holder.title.text = currentItem.title
        holder.quantity.text = "1"
        holder.buy.text = currentItem.price + " €"

        holder.bind(currentItem, context)
    }

    class ChartViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val title : TextView = itemView.findViewById(R.id.shoppingcart_item_name)
        val quantityAddButton : Button = itemView.findViewById(R.id.shoppingcart_btn_add)
        val quantity : TextView = itemView.findViewById(R.id.shoppingcart_item_quantity)
        val quantitySubstractButton : Button = itemView.findViewById(R.id.shoppingcart_btn_substract)
        val buy : TextView = itemView.findViewById(R.id.shoppingcart_item_price)

        fun bind(dishItem : DishItem, context: Context) {
            quantityAddButton.setOnClickListener {
                val newQuantity = Integer.parseInt(quantity.text.toString()) + 1
                quantity.text = newQuantity.toString()
            }

            quantitySubstractButton.setOnClickListener {
                if (Integer.parseInt(quantity.text.toString()) > 1){
                    val newQuantity = Integer.parseInt(quantity.text.toString()) - 1
                    quantity.text = newQuantity.toString()
                }
            }
        }
    }
}