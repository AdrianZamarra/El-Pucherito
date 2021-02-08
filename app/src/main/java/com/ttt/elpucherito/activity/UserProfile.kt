package com.ttt.elpucherito.activity

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.ttt.elpucherito.R
import kotlinx.android.synthetic.main.activity_modifyuser.*

class UserProfile : AppCompatActivity(), View.OnClickListener {

    var drawerLayout : DrawerLayout? = null
    var imageMenu : ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userprofile)

        drawerLayout = findViewById(R.id.drawerLayout)
        imageMenu = findViewById(R.id.imageMenu)

        imageMenu!!.setOnClickListener(this)


   }

    override fun onClick(p0: View?) {
        drawerLayout?.openDrawer(GravityCompat.START)
    }
}