package com.ttt.elpucherito.activities.users

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.ttt.elpucherito.R
import com.ttt.elpucherito.db.ElPucheritoDB
import com.ttt.elpucherito.db.entities.User
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
        val phone = signEtPhone?.text.toString()
        val email = signEtEmail?.text.toString()
        val pass = signEtPassword?.text.toString()

        if (name.equals("") || surname.equals("") || address.equals("") || phone.equals("") || email.equals("") || pass.equals("") ){
            signEtName?.setText("")
            signEtSurname?.setText("")
            signEtAddress?.setText("")
            signEtEmail?.setText("")
            signEtPassword?.setText("")
            signEtPhone?.setText("")

            Toast.makeText(this,"¡Tienes que rellenar todos los campos!",Toast.LENGTH_SHORT).show()
        }else{
            val user = User(null, name, surname, address, phone.toInt(), email, pass,0)

            Thread {

                val db: ElPucheritoDB = ElPucheritoDB.getInstance(this)

                launch {
                    db.userDao().insertUser(user)
                }
            }.start()
            val logScreen = Intent(this, LoginActivity::class.java)
            startActivity(logScreen)
        }
    }
    override fun onClick(p0: View?) {
        collectData()
    }

    override fun onBackPressed() {
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
    }

    override fun onPause() {
        super.onPause()
        finish()
    }

}