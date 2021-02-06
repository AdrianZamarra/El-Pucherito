package com.ttt.elpucherito.db.entity

import androidx.room.*



@Entity(tableName = "users")
class User(
    @PrimaryKey(autoGenerate = true) val user_id: Int?,

    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "surname") val surname: String,
    @ColumnInfo(name = "address") val address: String,
    @ColumnInfo(name = "phonenum") val phonenum: Int,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "password") val password: String

)
