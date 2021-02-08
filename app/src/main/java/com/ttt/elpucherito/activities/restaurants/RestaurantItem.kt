package com.ttt.elpucherito.activities.restaurants

import java.io.Serializable

data class RestaurantItem(val resturant_id : Int?, val image: String, val name : String, val address : String, val category : String) : Serializable