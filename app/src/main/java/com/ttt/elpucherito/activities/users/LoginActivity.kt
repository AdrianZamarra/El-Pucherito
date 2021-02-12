package com.ttt.elpucherito.activities.users

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.ttt.elpucherito.R
import com.ttt.elpucherito.activities.restaurants.RestaurantsActivity

import com.ttt.elpucherito.db.ElPucheritoDB
import com.ttt.elpucherito.db.entities.ShoppingCart
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext



class LoginActivity : AppCompatActivity(), View.OnClickListener, CoroutineScope {

    private var loginTvLogin : TextView ? = null
    private var loginEtEmail : EditText ? = null
    private var loginEtPassword : EditText ? = null
    private var loginBtnEnter : Button ? = null
    private var loginBtnSingin : Button ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginTvLogin = findViewById(R.id.login_tv_login)
        loginEtEmail = findViewById(R.id.login_et_email)
        loginEtPassword = findViewById(R.id.login_et_password)
        loginBtnEnter = findViewById(R.id.login_btn_enter)
        loginBtnSingin = findViewById(R.id.login_btn_singin)

        loginBtnEnter!!.setOnClickListener(this)
    }

    fun goSignin(view: View){
        var goSignScreen = Intent(this,SignActivity::class.java)
        startActivity(goSignScreen)
    }

    override fun onClick(p0: View?) {
        login()
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    fun login(){

        val email = loginEtEmail?.text.toString()
        val pass = loginEtPassword?.text.toString()

        if(email == "" || pass == "") {

            Toast.makeText(this, getString(R.string.emptyFields), Toast.LENGTH_SHORT).show()

        }else{

            Thread {

                var db: ElPucheritoDB = ElPucheritoDB.getInstance(this)
                val user = db.userDao().getValidateUser(email, pass)

                var shoppingCart: ShoppingCart = db.shoppingCartDao().getActiveShoppingCartFromUserID(user.user_id!!)

                if (user != null) {

                    user.logged = 1
                    db.userDao().updateUser(user)

                    launch {
                        if(shoppingCart == null){
                            db.shoppingCartDao().insertShoppingCart(ShoppingCart(null,null,1,user.user_id!!))
                        }
                    }

                    val restaurantScreen = Intent(this, RestaurantsActivity::class.java)
                    startActivity(restaurantScreen)

                }
            }.start()
        }
        loginEtEmail?.setText("")
        loginEtPassword?.setText("")

    }
    override fun onPause() {
        super.onPause()
        this.finish()
    }
}

