package com.ttt.elpucherito.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ttt.elpucherito.R
import com.ttt.elpucherito.activities.restaurant.RestaurantActivity
import com.ttt.elpucherito.activities.restaurants.RestaurantsActivity

import com.ttt.elpucherito.activities.shoppingcart.CheckoutActivity
import com.ttt.elpucherito.activities.users.LoginActivity
import com.ttt.elpucherito.db.ElPucheritoDB
import com.ttt.elpucherito.db.entities.User


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        Thread {
            val db: ElPucheritoDB = ElPucheritoDB.getInstance(this)
            val user: User = db.userDao().getLoggedUser()
            if (user != null) {
                var intent = Intent(this, RestaurantsActivity::class.java)
               startActivity(intent)
            } else {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }.start()
    }
}

