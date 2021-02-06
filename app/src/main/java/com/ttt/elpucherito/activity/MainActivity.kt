package com.ttt.elpucherito.activity

import android.graphics.Bitmap
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ttt.elpucherito.R
import com.ttt.elpucherito.db.entity.ElPucheritoDB
import com.ttt.elpucherito.db.entity.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val thread = Thread {
            var db: ElPucheritoDB = ElPucheritoDB.getInstance(this)
            println("${Thread.currentThread()} has run.")
            var user : User = User(null,"Adri","Mazarra","Calle la sierra",123123132,"orangu@gmail.com","a")

            launch {

                db.userDao().insertUser(user)
            }




            val messageList: List<User> =  db.userDao().getUsers()
            println("asdasads->>>> " + messageList)
        }
        thread.start()



    }


}
