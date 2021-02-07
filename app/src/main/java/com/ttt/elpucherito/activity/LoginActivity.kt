package com.ttt.elpucherito.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.StringDef
import androidx.appcompat.app.AppCompatActivity
import com.ttt.elpucherito.R
import com.ttt.elpucherito.db.ElPucheritoDB
import com.ttt.elpucherito.db.entity.User
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private var login_tv_login : TextView ? = null
    private var login_et_email : EditText ? = null
    private var login_et_password : EditText ? = null
    private var login_cb_stayin : CheckBox ? = null
    private var login_btn_enter : Button ? = null
    private var login_btn_singin : Button ? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_tv_login = findViewById(R.id.login_tv_login)
        login_et_email = findViewById(R.id.login_et_email)
        login_et_password = findViewById(R.id.login_et_password)
        login_cb_stayin = findViewById(R.id.login_cb_stayin)
        login_btn_enter = findViewById(R.id.login_btn_enter)

        login_btn_singin = findViewById(R.id.login_btn_singin)

        // Implemento setOnClickListener
        login_btn_enter!!.setOnClickListener(this)



    }


    fun goSignin(view: View){
        var goSignScreen = Intent(this,SignActivity::class.java)
        startActivity(goSignScreen)
    }

    override fun onClick(p0: View?) {
        login()
    }



    fun login(){

        val email = login_et_email?.text.toString()
        val pass = login_et_password?.text.toString()

        val thread = Thread {

            var db: ElPucheritoDB = ElPucheritoDB.getInstance(this)

            val user = db.userDao().getValidateUser(email, pass)

            if (user == null) {

                // TOAST y reiniciar campos
                Toast.makeText(this, getString(R.string.invalidUser), Toast.LENGTH_SHORT)
                login_et_email?.text!!.clear()
                login_et_password?.text!!.clear()
                
                println(db.userDao().getUsers())


            } else {


                // INTENT A SCREEN RESTAURANTES
                //val restaurantScreen = Intent(this,RestaurantsActivity::class.java)
                //startActivity(restaurantScreen)

            }
        }
        }

    }

