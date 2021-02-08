package com.ttt.elpucherito.db.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.ttt.elpucherito.db.entities.Assessment
import com.ttt.elpucherito.db.entities.Restaurant

data class RestaurantWithAssessments(
    @Embedded val restaurant: Restaurant?,
    @Relation(
        parentColumn = "restaurant_id",
        entityColumn = "restaurant_id"
    )
    val assessments : List<Assessment>
)