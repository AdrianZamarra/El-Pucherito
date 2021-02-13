package com.ttt.elpucherito.activities.restaurants

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
import com.ttt.elpucherito.activities.restaurant.RestaurantActivity
import com.ttt.elpucherito.db.ElPucheritoDB
import java.lang.Exception

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
        holder.category.text = currentItem.category
        try {
            holder.assesment.rating = getAvgAssessment(currentItem)
        }catch (e : Exception){

            holder.assesment.rating = 0f
        }

        holder.bind(currentItem, context)
    }

    @Throws(Exception::class)
    private fun getAvgAssessment(restaurant : RestaurantItem) : Float{
        var nAssesments = 0
        var finalAssessment = 0f
        Thread {
                val db = ElPucheritoDB.getInstance(context).assessmentDao().getAssessments()
                db.forEach {
                    if (it.restaurant_id == restaurant.resturant_id){
                        finalAssessment += it.rating
                        nAssesments += 1
                    }
                }
        }.start()
        Thread.sleep(10)
        return finalAssessment / nAssesments
    }

    class ChartViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val name : TextView = itemView.findViewById(R.id.restaurants_name)
        var image : ImageView = itemView.findViewById(R.id.restaurants_image)
        val assesment : RatingBar = itemView.findViewById(R.id.restaurants_rating)
        val category : TextView = itemView.findViewById(R.id.restaurants_tv_category)
        fun bind(restaurant : RestaurantItem, context: Context) {
            image.setOnClickListener {
                val intent = Intent(context, RestaurantActivity::class.java)
                intent.putExtra("Restaurant", restaurant)
                context.startActivity(intent)
            }
        }
    }
}