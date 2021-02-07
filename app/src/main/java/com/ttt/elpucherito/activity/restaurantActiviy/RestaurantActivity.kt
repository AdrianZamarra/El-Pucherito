package com.ttt.elpucherito.activity.restaurantActiviy

import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ttt.elpucherito.R
import com.ttt.elpucherito.activity.restaurantsActivity.RestaurantItem
import com.ttt.elpucherito.db.ElPucheritoDB
import com.ttt.elpucherito.db.relations.RestaurantWithDishes

class RestaurantActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant)

        val restaurantAvatar : ImageView = findViewById(R.id.restaurant_avatar)
        val restaurantTvName : TextView  = findViewById(R.id.restaurant_tv_name)
        val restaurantAssesment : RatingBar = findViewById(R.id.restaurant_ratingbar)

        val restaurant = intent.getSerializableExtra("Restaurant") as RestaurantItem

        restaurantTvName.text = restaurant.name

        val id = this.resources.getIdentifier(restaurant.image, "drawable", this.packageName)
        restaurantAvatar.setImageResource(id)

        val dishes : ArrayList<DishItem> = ArrayList()

        val recyclerView : RecyclerView = findViewById(R.id.restaurant_chart_dishes)
        recyclerView.adapter = ChartAdapter(dishes,this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
    }

    private fun getRatingOfUser() : Int {

        var rating : Int = 0

        Thread{
            var db : ElPucheritoDB = ElPucheritoDB.getInstance(this)

            var restaurants = db.restaurantDao().getRestaurants()
            var users = db.userDao().getUsers()
            
        }.start()

        return rating
    }


    private fun getDishesFromRestaurant() : ArrayList<DishItem> {

        val dishList : List<RestaurantWithDishes>
        val dishItems : ArrayList<DishItem> = ArrayList()

        val db : ElPucheritoDB = ElPucheritoDB.getInstance(this)

        dishList = db.restaurantDao().getDishes()

        return dishItems

    }
}
