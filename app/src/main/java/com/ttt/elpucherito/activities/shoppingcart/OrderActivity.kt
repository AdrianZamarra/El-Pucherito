package com.ttt.elpucherito.activities.shoppingcart
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.ttt.elpucherito.R
import com.ttt.elpucherito.activities.restaurants.RestaurantsActivity
import com.ttt.elpucherito.db.ElPucheritoDB
import com.ttt.elpucherito.db.entities.DishShoppingCartRef
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class OrderActivity : AppCompatActivity() {
    private var expandableListView: ExpandableListView? = null
    private var adapter: ExpandableListAdapter? = null
    private var titleList: List<String>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        val imageArrow : ImageView = findViewById(R.id.imageArrow)

        imageArrow.setOnClickListener(View.OnClickListener {
            var intent = Intent(this, RestaurantsActivity::class.java)
            startActivity(intent)
        })

        //title = "KotlinApp"
        expandableListView = findViewById(R.id.expendableList)
        if (expandableListView != null) {
            val listData = getDatesOrders()
            var tvEmpty:TextView = findViewById(R.id.tv_empty_order)
            if(listData.isEmpty()){

                tvEmpty.text = getString(R.string.non_existing_products)

            }else{
                tvEmpty.text = ""
            }
            titleList = ArrayList(listData.keys)
            adapter = OrderExpandableListAdapter(this, titleList as ArrayList<String>, listData)

            expandableListView!!.setAdapter(adapter)
        }
    }

    private fun getDatesOrders() : MutableMap<String, List<String>>{

        var dateOrders : MutableMap<String, List<String>> = HashMap()
        Thread {

            val db : ElPucheritoDB = ElPucheritoDB.getInstance(this)

            val orders = db.shoppingCartDao().getOrders()
            var totalPrice = 0f
            orders.forEach{

                val mutableList: MutableList<String> = ArrayList()
                val dishes = db.dishShoppingCartDao().getDishesWithShoppingCartID(it.shopping_cart_id!!)

                dishes.forEach {
                    var quantity = db.dishShoppingCartDao().getDishQuantityWithDishIDAndShoppingCartID(it.dish_id,it.shopping_cart_id)
                    var dish = db.dishDao().getDishByID(it.dish_id)
                    mutableList.add("${dish.name} \n  \n ${dish.price}€ x $quantity \n")
                    totalPrice += dish.price * quantity
                }
                mutableList.add("Precio Total ${totalPrice}€")
                totalPrice = 0f

                val formatter = SimpleDateFormat("dd-MM-yyyy HH:mm")
                val time = it.purchase_date
                dateOrders[formatter.format(time!!).toString()] = mutableList
            }
        }.start()

        Thread.sleep(100)

        return TreeMap(dateOrders).toSortedMap(reverseOrder())
    }

    override fun onBackPressed() {
        startActivity(Intent(this, RestaurantsActivity::class.java))
    }

    override fun onPause() {
        super.onPause()
        this.finish()
    }
}
