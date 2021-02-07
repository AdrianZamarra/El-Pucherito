package com.ttt.elpucherito.activity.restaurantsActivity

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ttt.elpucherito.R
import com.ttt.elpucherito.activity.restaurantActiviy.RestaurantActivity

class RestaurantAdapter(private val restaurantsList : List<RestaurantItem>, private val context: Context) : RecyclerView.Adapter<RestaurantAdapter.ChartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChartViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.restaurants_list, parent, false)

        return ChartViewHolder(
            itemView
        )
    }

    override fun getItemCount() = restaurantsList.size

    override fun onBindViewHolder(holder: ChartViewHolder, position: Int) {
        val currentItem = restaurantsList[position]

        holder.name.text = currentItem.name
        val id = context.resources.getIdentifier(currentItem.image, "drawable", context.packageName)
        holder.image.setImageResource(id)
        holder.assesment.rating = currentItem.assesment
        holder.bind(currentItem, context)
    }

    class ChartViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val name : TextView = itemView.findViewById(R.id.restaurants_name)
        var image : ImageView = itemView.findViewById(R.id.restaurants_image)
        val assesment : RatingBar = itemView.findViewById(R.id.restaurants_rating)
        fun bind(restaurant : RestaurantItem, context: Context) {
            image.setOnClickListener {

                var intent = Intent(context, RestaurantActivity::class.java)
                intent.putExtra("Restaurant", restaurant)

                context.startActivity(intent)
                Toast.makeText(context, restaurant.name, Toast.LENGTH_SHORT).show() }
        }
    }
}