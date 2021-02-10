package com.ttt.elpucherito.activities.shoppingcart

import java.util.*
internal object OrderExpandableListData {
    val data: HashMap<String, List<String>>
        get() {
            val expandableListDetail = HashMap<String, List<String>>()
            val myFavCricketPlayers: MutableList<String> =
                ArrayList()
            myFavCricketPlayers.add("MS.Dhoni")
            myFavCricketPlayers.add("Sehwag")
            myFavCricketPlayers.add("Shane Watson")
            myFavCricketPlayers.add("Ricky Ponting")
            myFavCricketPlayers.add("Shahid Afridi")
            expandableListDetail["FECHA DEL PEDIDO"] = myFavCricketPlayers
            return expandableListDetail
        }
}