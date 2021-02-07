package com.ttt.elpucherito.activity.restaurantsActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ttt.elpucherito.R
import com.ttt.elpucherito.db.ElPucheritoDB
import com.ttt.elpucherito.db.entity.Restaurant

class RestaurantsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurants)

        val restaurantItems = getRestaurants()

        val recyclerView : RecyclerView = findViewById(R.id.restaurants_recycler)
        recyclerView.adapter = RestaurantAdapter(restaurantItems, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
    }

    /**
     * Get Every restaurant of the database and adds it to an arraylist
     */
    private fun getRestaurants() : ArrayList<RestaurantItem>{

        val restaurantsList : List<Restaurant>
        val restaurantItems : ArrayList<RestaurantItem> = ArrayList()

        val db : ElPucheritoDB = ElPucheritoDB.getInstance(this)

        restaurantsList = db.restaurantDao().getRestaurants()

        restaurantsList.forEach { restaurantItems.add(RestaurantItem(it.image, it.name, it.address)) }

        return restaurantItems
    }

}