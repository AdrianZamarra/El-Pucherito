package com.ttt.elpucherito.activity.restaurantActiviy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ttt.elpucherito.R
import com.ttt.elpucherito.activity.restaurantsActivity.RestaurantItem
import com.ttt.elpucherito.db.ElPucheritoDB
import com.ttt.elpucherito.db.entity.Dish
import com.ttt.elpucherito.db.entity.Restaurant
import com.ttt.elpucherito.db.relations.RestaurantWithDishes

class RestaurantActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant)

        val dishes : ArrayList<DishItem> = ArrayList()

        val recyclerView : RecyclerView = findViewById(R.id.restaurant_chart_dishes)
        recyclerView.adapter = ChartAdapter(dishes,this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
    }


    private fun getDishesFromRestaurant() : ArrayList<DishItem> {

        val dishList : List<RestaurantWithDishes>
        val dishItems : ArrayList<DishItem> = ArrayList()

        val db : ElPucheritoDB = ElPucheritoDB.getInstance(this)

        dishList = db.restaurantDao().getDishes()

        return dishItems

    }
}
