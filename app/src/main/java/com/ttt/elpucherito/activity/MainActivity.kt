package com.ttt.elpucherito.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ttt.elpucherito.R
import com.ttt.elpucherito.activity.restaurantActiviy.RestaurantActivity

import com.ttt.elpucherito.activity.restaurantsActivity.RestaurantsActivity
import com.ttt.elpucherito.db.ElPucheritoDB
import com.ttt.elpucherito.db.entity.User


class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Thread{

            var db: ElPucheritoDB = ElPucheritoDB.getInstance(this)

            var user: User = db.userDao().getLoggedUser()

            if (user != null) {
                var intent = Intent(this, RestaurantActivity::class.java)

            }else{
                var intent = Intent(this, LoginActivity::class.java)

            }
            startActivity(intent)
        }.start()




    }
}
