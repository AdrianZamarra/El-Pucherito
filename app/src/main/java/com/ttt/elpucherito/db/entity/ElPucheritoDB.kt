package com.ttt.elpucherito.db.entity

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase


@Database(entities = [User::class], version = 1)
abstract class ElPucheritoDB : RoomDatabase() {


companion object {
    var  db: ElPucheritoDB? = null;
        fun getInstance(context: Context): ElPucheritoDB {

            if (db == null) {
                db = databaseBuilder(
                    context.getApplicationContext(),
                    ElPucheritoDB::class.java,
                    "database-name"
                ).build();
            }
            return db as ElPucheritoDB;
    }
}

 abstract fun userDao():UserDao;
}
