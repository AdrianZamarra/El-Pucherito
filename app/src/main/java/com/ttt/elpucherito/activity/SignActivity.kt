package com.ttt.elpucherito.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.ttt.elpucherito.R
import com.ttt.elpucherito.db.ElPucheritoDB
import com.ttt.elpucherito.db.entity.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class SignActivity : AppCompatActivity(), View.OnClickListener, CoroutineScope {

    private var signTvSignin : TextView ? = null
    private var signEtEmail : EditText ? = null
    private var signEtAddress : EditText ? = null
    private var signEtPassword : EditText ? = null
    private var signBtnEnter : Button ? = null

    private var signEtName : EditText ? = null
    private var signEtSurname : EditText ? = null
    private var signEtPhone : EditText ? = null

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)

        signTvSignin = findViewById(R.id.sign_tv_signin)
        signBtnEnter = findViewById(R.id.sign_btn_enter)

        signEtName = findViewById(R.id.sign_et_name)
        signEtSurname = findViewById(R.id.sign_et_surname)
        signEtAddress = findViewById(R.id.sign_et_address)
        signEtEmail = findViewById(R.id.sign_et_email)
        signEtPassword = findViewById(R.id.sign_et_password)
        signEtPhone = findViewById(R.id.sign_et_phonenumber)

        // Implemento setOnClickListener
        signBtnEnter!!.setOnClickListener(this)

    }

    /*
    * Function of collecting data
    * Insert data in DB
    */
    fun collectData() {

        val name = signEtName?.text.toString()
        val surname = signEtSurname?.text.toString()
        val address = signEtAddress?.text.toString()
        val phone = signEtPhone?.text.toString().toInt()
        val email = signEtEmail?.text.toString()
        val pass = signEtPassword?.text.toString()

        var user = User(null, name, surname, address, phone, email, pass,0)

        println(user.name)
        Thread {

            var db: ElPucheritoDB = ElPucheritoDB.getInstance(this)

            launch {
                db.userDao().insertUser(user)
            }
        }.start()
    }
    override fun onClick(p0: View?) {
        collectData()

        val logScreen = Intent(this,LoginActivity::class.java)
        startActivity(logScreen)
    }

}