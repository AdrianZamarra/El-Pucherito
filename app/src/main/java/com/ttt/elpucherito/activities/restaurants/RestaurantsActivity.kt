package com.ttt.elpucherito.activities.restaurants

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.get
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.ttt.elpucherito.R
import com.ttt.elpucherito.activities.shoppingcart.OrderActivity
import com.ttt.elpucherito.activities.shoppingcart.ShoppingCartActivity
import com.ttt.elpucherito.activities.users.LoginActivity
import com.ttt.elpucherito.activities.users.ModifyProfile
import com.ttt.elpucherito.db.ElPucheritoDB
import com.ttt.elpucherito.db.entities.Restaurant


class RestaurantsActivity : AppCompatActivity() {

    var nameProfile : TextView?= null
    var menu : Menu?= null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurants)

        val restaurantItems = getRestaurants()

        val recyclerView : RecyclerView = findViewById(R.id.restaurants_recycler)
        recyclerView.adapter = RestaurantAdapter(restaurantItems, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        val drawerLayout : DrawerLayout? = findViewById(R.id.drawerLayout)
        val imageMenu : ImageView?= findViewById(R.id.imageMenu)

        val imageCart : ImageView?= findViewById(R.id.img_carrito)
        imageCart!!.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, ShoppingCartActivity::class.java)
            startActivity(intent)
        } )

        nameProfile = this.findViewById(R.id.nameProfile)
        nameProfile?.text = ""

        Thread{

            val db : ElPucheritoDB = ElPucheritoDB.getInstance(this)
            val user = db.userDao().getLoggedUser()

            nameProfile!!.append(user.name)
            var navigationView : NavigationView = findViewById(R.id.navigationView)

            navigationView.menu.findItem(R.id.menuSurname).setVisible(false).title = user.surname
            navigationView.menu.findItem(R.id.menuPhone).title = user.phonenum.toString()
            navigationView.menu.findItem(R.id.menuAddress).title = user.address
            navigationView.menu.findItem(R.id.menuEmail).title = user.email
            navigationView.menu.findItem(R.id.menuPassword).setVisible(false).title = "**********"

        }.start()

        imageMenu!!.setOnClickListener {drawerLayout?.openDrawer(GravityCompat.START)  }
        
        val btnLogOut : Button = findViewById(R.id.btn_logout)
        btnLogOut.setOnClickListener {logOut(this)}

        val btnOrder : Button = findViewById(R.id.btn_order)
        btnOrder.setOnClickListener {
            var intent = Intent(this, OrderActivity::class.java)
            startActivity(intent)
        }

        val modifyProfile : Button = findViewById(R.id.btn_modify)
        modifyProfile.setOnClickListener {modifyView(this)}

    }

    private fun getRestaurants() : ArrayList<RestaurantItem>{

        var restaurantsList : List<Restaurant>
        val restaurantItems : ArrayList<RestaurantItem> = ArrayList()

        Thread {
            val db : ElPucheritoDB = ElPucheritoDB.getInstance(this)

            restaurantsList = db.restaurantDao().getRestaurants()
            restaurantsList.forEach {
                restaurantItems.add(
                    RestaurantItem(
                        it.restaurant_id,
                        it.image,
                        it.name,
                        it.address,
                        it.category
                    )
                )
            }
        }.start()
        return restaurantItems
    }

    private fun logOut(context: Context) {
        Thread {
            var db: ElPucheritoDB = ElPucheritoDB.getInstance(context)

            val user = db.userDao().getLoggedUser()
            if (user != null) {

                user.logged = 0
                db.userDao().updateUser(user)
                var intent = Intent(context, LoginActivity::class.java)
                startActivity(intent)
            }
        }.start()
    }

    private fun modifyView(context: Context){
        var intent = Intent(context, ModifyProfile::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {
        finish()
    }
    private fun showShoppingCart(context: Context){
        var intent = Intent(context, ShoppingCartActivity::class.java)
        startActivity(intent)
    }

    override fun onPause() {
        super.onPause()
        this.finish()
    }
}
