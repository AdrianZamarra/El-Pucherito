package com.ttt.elpucherito.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ttt.elpucherito.db.dao.*
import com.ttt.elpucherito.db.entity.*
import com.ttt.elpucherito.util.Converters
import com.ttt.elpucherito.util.getJsonDataFromAsset
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

@TypeConverters(Converters::class)
@Database(entities = [User::class, Dish::class, Restaurant::class,ShoppingCart::class,Assessment::class,DishesShoppingCarts::class], version = 2)
abstract class ElPucheritoDB : RoomDatabase(), CoroutineScope {

    abstract fun userDao(): UserDao;
    abstract fun restaurantDao(): RestaurantDao;
    abstract fun dishDao(): DishDao;
    abstract fun shoppingCartDao(): ShoppingCartDao;
    abstract fun assessmentDao(): AssessmentDao;

companion object {
    var  db: ElPucheritoDB? = null;
        fun getInstance(context: Context): ElPucheritoDB {

            if (db == null) {
                db = databaseBuilder(
                    context.getApplicationContext(),
                    ElPucheritoDB::class.java,
                    "el_pucherito_db"
                ).build();
                db!!.fillRestaurantsFromJsonPath(context.getApplicationContext(),"restaurants.json")
                db!!.fillDishesFromJsonPath(context.getApplicationContext(),"dishes.json")

            }
            return db as ElPucheritoDB;
    }

    private fun fillRestaurantsFromJsonPath(applicationContext: Context?, s: String) {

    }


}
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

        fun fillRestaurantsFromJsonPath(context: Context, jsonPath: String){

            val jsonFileString = getJsonDataFromAsset(context, jsonPath)


            val gson = Gson()
            val restaurantList = object : TypeToken<List<Restaurant>>() {}.type
            var restaurants: List<Restaurant> = gson.fromJson(jsonFileString, restaurantList)

            var db: ElPucheritoDB =
                getInstance(
                    context
                )
            launch {
                restaurants.forEach { restaurant ->   db.restaurantDao().insert(restaurant) }
            }

        }
    fun fillDishesFromJsonPath(context: Context, jsonPath: String){

        val jsonFileString = getJsonDataFromAsset(context, jsonPath)

        val gson = Gson()
        val dishesList = object : TypeToken<List<Dish>>() {}.type
        var dishes: List<Dish> = gson.fromJson(jsonFileString, dishesList)

        var db: ElPucheritoDB =
            getInstance(
                context
            )
        launch {
            dishes.forEach { dish ->   db.dishDao().insertDish(dish) }
        }

    }
    fun fillUsersFromJsonPath(context: Context, jsonPath: String){

        val jsonFileString = getJsonDataFromAsset(context, jsonPath)

        val gson = Gson()
        val UserList = object : TypeToken<List<User>>() {}.type
        var users: List<User> = gson.fromJson(jsonFileString, UserList)

        var db: ElPucheritoDB =
            getInstance(
                context
            )
        launch {
            users.forEach { user ->   db.userDao().insertUser(user) }
        }

    }
    fun printAllEntities(context: Context){

        var db : ElPucheritoDB = ElPucheritoDB.getInstance(context)

        val thread = Thread {
            print("USERS")
            db.userDao().getUsers().forEach { println(it) }
            print("RESTAURANTS")
            db.restaurantDao().getRestaurants().forEach { println(it) }
            print("DISHES")
            db.dishDao().getDishes().forEach { println(it) }
        }

        thread.start()
    }



}



