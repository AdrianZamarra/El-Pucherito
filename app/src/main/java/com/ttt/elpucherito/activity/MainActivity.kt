package com.ttt.elpucherito.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ttt.elpucherito.R

import com.ttt.elpucherito.activity.restaurantsActivity.RestaurantsActivity


class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var i = Intent(this, RestaurantsActivity::class.java)
        startActivity(i)


    }
}
