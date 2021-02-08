package com.ttt.elpucherito.db.daos


import androidx.room.*
import com.ttt.elpucherito.db.entities.Assessment

@Dao
interface AssessmentDao {

    @Query("SELECT * FROM assessments ORDER BY assessments_id ASC")
    fun getAssessments(): List<Assessment>

    @Query("SELECT * FROM assessments WHERE user_email=:email and restaurant_id= :restaurant_id")
    fun getAssessmentByEmailAndRestaurantID(email:String,restaurant_id:Int): Assessment

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAssessments(assessment: Assessment)

    @Update
    suspend fun updateAssessment(assessment: Assessment)

    @Query("DELETE FROM assessments")
    suspend fun deleteAll()

}
