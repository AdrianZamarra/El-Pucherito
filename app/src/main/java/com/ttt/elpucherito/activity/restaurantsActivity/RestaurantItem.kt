package com.ttt.elpucherito.activity.restaurantsActivity

import java.io.Serializable

data class RestaurantItem(val image: String, val name : String, val address : String, val category : String, val assesment : Float) : Serializable