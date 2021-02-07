package com.ttt.elpucherito.db.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ttt.elpucherito.db.entity.Assessment
import com.ttt.elpucherito.db.entity.User

@Dao
interface AssessmentDao {

    @Query("SELECT * FROM assessments ORDER BY assessments_id ASC")
    fun getAssessments(): List<Assessment>

    @Query("SELECT * FROM assessments WHERE user_email=:email and restaurant_id= :restaurant_id")
    fun getAssessmentByEmailAndRestaurantID(email:String,restaurant_id:Int): Assessment

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAssessments(assessment: Assessment)

    @Query("DELETE FROM assessments")
    suspend fun deleteAll()

}
