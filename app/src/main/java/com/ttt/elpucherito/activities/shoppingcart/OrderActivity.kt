package com.ttt.elpucherito.activities.shoppingcart
import android.content.Intent
import android.os.Bundle
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ttt.elpucherito.R
import com.ttt.elpucherito.activities.restaurants.RestaurantsActivity
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
        title = "KotlinApp"
        expandableListView = findViewById(R.id.expendableList)
        if (expandableListView != null) {
            val listData = getDatesOrders()
            titleList = ArrayList(listData.keys)
            adapter = OrderExpandableListAdapter(this, titleList as ArrayList<String>, listData)

            expandableListView!!.setAdapter(adapter)
            expandableListView!!.setOnGroupExpandListener { groupPosition ->
                Toast.makeText(
                    applicationContext,
                    (titleList as ArrayList<String>)[groupPosition] + " List Expanded.",
                    Toast.LENGTH_SHORT
                ).show()
            }
            expandableListView!!.setOnGroupCollapseListener { groupPosition ->
                Toast.makeText(
                    applicationContext,
                    (titleList as ArrayList<String>)[groupPosition] + " List Collapsed.",
                    Toast.LENGTH_SHORT
                ).show()
            }
            expandableListView!!.setOnChildClickListener { _, _, groupPosition, childPosition, _ ->
                Toast.makeText(
                    applicationContext,
                    "Clicked: " + (titleList as ArrayList<String>)[groupPosition] + " -> " + listData[(
                            titleList as
                                    ArrayList<String>
                            )
                            [groupPosition]]!!.get(
                        childPosition
                    ),
                    Toast.LENGTH_SHORT
                ).show()
                false
            }
        }
    }

    private fun getDatesOrders() : MutableMap<String, List<String>>{

        var dateOrders : MutableMap<String, List<String>> = HashMap()

        for (i in 1..9){
            var myFavCricketPlayers: MutableList<String> = ArrayList()
            myFavCricketPlayers.add("Plato 1")
            myFavCricketPlayers.add("Plato 2")
            myFavCricketPlayers.add("Plato 3")
            myFavCricketPlayers.add("Plato 4")
            myFavCricketPlayers.add("Plato 5")
            dateOrders["$i/01/2021"] = myFavCricketPlayers
        }
        return TreeMap(dateOrders)
    }

    override fun onBackPressed() {
        startActivity(Intent(this, RestaurantsActivity::class.java))
    }

    override fun onPause() {
        super.onPause()
        this.finish()
    }

}
