package com.ttt.elpucherito.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.ttt.elpucherito.R
import com.ttt.elpucherito.activity.restaurantsActivity.RestaurantsActivity
import com.ttt.elpucherito.db.ElPucheritoDB


class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private var loginTvLogin : TextView ? = null
    private var loginEtEmail : EditText ? = null
    private var loginEtPassword : EditText ? = null
    private var loginCbStayin : CheckBox ? = null
    private var loginBtnEnter : Button ? = null
    private var loginBtnSingin : Button ? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)



        loginTvLogin = findViewById(R.id.login_tv_login)
        loginEtEmail = findViewById(R.id.login_et_email)
        loginEtPassword = findViewById(R.id.login_et_password)
        loginCbStayin = findViewById(R.id.login_cb_stayin)
        loginBtnEnter = findViewById(R.id.login_btn_enter)

        loginBtnSingin = findViewById(R.id.login_btn_singin)

        loginBtnEnter!!.setOnClickListener(this)
    }


    fun goSignin(view: View){
        var goSignScreen = Intent(this,SignActivity::class.java)
        startActivity(goSignScreen)
    }

    override fun onClick(p0: View?) {
        println("entro en el login")
        login()
    }



    fun login(){

        val email = loginEtEmail?.text.toString()
        val pass = loginEtPassword?.text.toString()

        val thread = Thread {

            var db: ElPucheritoDB = ElPucheritoDB.getInstance(this)

            val user = db.userDao().getValidateUser(email, pass)
            if (user != null) {

                user.logged = 1
                db.userDao().updateUser(user)

                val restaurantScreen = Intent(this, RestaurantsActivity::class.java)
                startActivity(restaurantScreen)


            } else {
                //Toast.makeText(this, getString(R.string.invalidUser), Toast.LENGTH_SHORT)
                println("me cago en dios")


            }
        }.start()

        loginEtEmail?.setText("")
        loginEtPassword?.setText("")
    }

}

