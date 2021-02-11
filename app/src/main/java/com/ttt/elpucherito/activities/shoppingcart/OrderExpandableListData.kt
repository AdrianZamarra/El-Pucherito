package com.ttt.elpucherito.activities.shoppingcart

import java.util.*
internal object OrderExpandableListData {
    val data: HashMap<String, List<String>>
        get() {
            val expandableListDetail = HashMap<String, List<String>>()
            val myFavCricketPlayers: MutableList<String> =
                ArrayList()
            myFavCricketPlayers.add("Patatas")
            myFavCricketPlayers.add("Salmorejo")
            myFavCricketPlayers.add("Pollo asado")
            myFavCricketPlayers.add("Pizza")
            myFavCricketPlayers.add("Kebab")
            expandableListDetail["FECHA DEL PEDIDO"] = myFavCricketPlayers
            return expandableListDetail
        }
}