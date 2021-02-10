package com.ttt.elpucherito.db.entities

import androidx.room.*



@Entity(tableName = "users",indices = (arrayOf(Index( value = ["email","phonenum"], unique = true))))
data class User(
    @PrimaryKey(autoGenerate = true) val user_id: Int?,

    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "surname") var surname: String,
    @ColumnInfo(name = "address") var address: String,
    @ColumnInfo(name = "phonenum") var phonenum: Int,
    @ColumnInfo(name = "email") var email: String,
    @ColumnInfo(name = "password") var password: String,
    @ColumnInfo(name = "logged") var logged: Int

    )
