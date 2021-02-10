package com.ttt.elpucherito.activities.shoppingcart

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.ttt.elpucherito.R

class CheckoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        //val ivGif : ImageView = findViewById(R.id.checkout_iv_gif)
        val tvAddres : TextView = findViewById(R.id.checkout_tv_address)

        tvAddres.setText("Calle nueva nº 13")
        //showGif(ivGif, getDrawable(R.drawable.deliver)!!)


    }

    private fun showGif(image: ImageView, gif : Drawable) {
        Glide.with(applicationContext).asGif().load(gif).into(image)

    }
}