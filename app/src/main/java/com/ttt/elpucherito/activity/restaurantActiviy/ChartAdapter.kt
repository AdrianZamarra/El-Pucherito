package com.ttt.elpucherito.activity.restaurantActiviy

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ttt.elpucherito.R

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

    class ChartViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val title : TextView = itemView.findViewById(R.id.restaurant_dish_name)
        val description : TextView = itemView.findViewById(R.id.restaurant_dish_description)
        val buy : Button = itemView.findViewById(R.id.restaurant_dish_buy)

        fun bind(dishItem : DishItem, context: Context) {
            buy.setOnClickListener { Toast.makeText(context, dishItem.title, Toast.LENGTH_SHORT).show() }
        }
    }
}