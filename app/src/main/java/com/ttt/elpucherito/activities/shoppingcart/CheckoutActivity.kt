package com.ttt.elpucherito.activities.shoppingcart

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.ttt.elpucherito.R
import com.ttt.elpucherito.activities.restaurants.RestaurantsActivity
import com.ttt.elpucherito.db.ElPucheritoDB
import kotlin.random.Random

class CheckoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)


        val tvAddres : TextView = findViewById(R.id.checkout_tv_address)
        val btnReturn : ImageView = findViewById(R.id.btnBack)
        val tvArrive : TextView = findViewById(R.id.checkout_tv_succesfull)

        val random = (15..60).random()

        tvArrive.append(random.toString() + "Mins")
        btnReturn.setOnClickListener{
            var intent = Intent(this, RestaurantsActivity::class.java)
            startActivity(intent)
        }

        Thread {
            var db = ElPucheritoDB.getInstance(this)
            var user = db.userDao().getLoggedUser()

            tvAddres.text = user.address

        }.start()
    }

    override fun onBackPressed() {
        var intent = Intent(this, RestaurantsActivity::class.java)
        startActivity(intent)
    }
}