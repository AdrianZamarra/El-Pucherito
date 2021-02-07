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
import kotlinx.android.synthetic.main.activity_sign.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class SignActivity : AppCompatActivity(), View.OnClickListener, CoroutineScope {

    private var sign_tv_signin : TextView ? = null
    private var sign_et_email : EditText ? = null
    private var sign_et_address : EditText ? = null
    private var sign_et_password : EditText ? = null
    private var sign_btn_enter : Button ? = null

    private var sign_et_name : EditText ? = null
    private var sign_et_surname : EditText ? = null
    private var sign_et_phone : EditText ? = null

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)

        sign_tv_signin = findViewById<TextView>(R.id.sign_tv_signin)
        sign_btn_enter = findViewById<Button>(R.id.sign_btn_enter)

        sign_et_name = findViewById<EditText>(R.id.sign_et_name)
        sign_et_surname = findViewById<EditText>(R.id.sign_et_surname)
        sign_et_address = findViewById<EditText>(R.id.sign_et_address)
        sign_et_email = findViewById<EditText>(R.id.sign_et_email)
        sign_et_password = findViewById<EditText>(R.id.sign_et_password)
        sign_et_phone = findViewById<EditText>(R.id.sign_et_phonenumber)

        // Implemento setOnClickListener
        sign_btn_enter!!.setOnClickListener(this)

    }

    /*
    * Function of collecting data
    * Insert data in DB
    */
    fun collectData() {

        val name = sign_et_name?.text.toString()
        val surname = sign_et_surname?.text.toString()
        val address = sign_et_address?.text.toString()
        val phone = sign_et_phone?.text.toString().toInt()
        val email = sign_et_email?.text.toString()
        val pass = sign_et_password?.text.toString()

        var user: User = User(null, name, surname, address, phone, email, pass)

        val thread = Thread {

            var db: ElPucheritoDB = ElPucheritoDB.getInstance(this)

            launch {

                db.userDao().insertUser(user)

            }

        }

    }
    override fun onClick(p0: View?) {
        collectData()

        val logScreen = Intent(this,LoginActivity::class.java)
        startActivity(logScreen)
    }

}