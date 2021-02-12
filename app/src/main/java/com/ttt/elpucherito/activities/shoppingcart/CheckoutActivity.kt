package com.ttt.elpucherito.activities.shoppingcart

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ttt.elpucherito.R
import com.ttt.elpucherito.activities.restaurants.RestaurantsActivity
import com.ttt.elpucherito.db.ElPucheritoDB

class CheckoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        val tvAddres : TextView = findViewById(R.id.checkout_tv_address)
        val btnReturn : ImageView = findViewById(R.id.btnBack)
        val tvArrive : TextView = findViewById(R.id.checkout_tv_succesfull)


        btnReturn.setOnClickListener{
            val intent = Intent(this, RestaurantsActivity::class.java)
            startActivity(intent)
        }

        Thread {
            val db = ElPucheritoDB.getInstance(this)
            val user = db.userDao().getLoggedUser()

            val quantity = intent.getSerializableExtra("quantity") as Int
            val totalTime = 15 + quantity
            tvArrive.append("$totalTime Mins")
            tvAddres.text = user.address

        }.start()
    }

    override fun onBackPressed() {
        val intent = Intent(this, RestaurantsActivity::class.java)
        startActivity(intent)
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}