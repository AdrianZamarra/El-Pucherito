package com.ttt.elpucherito.activities.users

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.ttt.elpucherito.R
import com.ttt.elpucherito.activities.restaurants.RestaurantsActivity
import com.ttt.elpucherito.db.ElPucheritoDB

class ModifyProfile : AppCompatActivity(), View.OnClickListener {

    private var modifyTvmodify: TextView? = null
    private var modifyEtEmail: EditText? = null
    private var modifyEtAddress: EditText? = null
    private var modifyEtPassword: EditText? = null
    private var modifyBtnSave: Button? = null

    private var modifyEtName: EditText? = null
    private var modifyEtSurname: EditText? = null
    private var modifyEtPhone: EditText? = null
    private var btnArrowBack : ImageView?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify_profile)

        modifyTvmodify = findViewById(R.id.modify_tv_modify)
        modifyBtnSave = findViewById(R.id.modify_btn_save)

        modifyEtName = findViewById(R.id.modify_et_name)
        modifyEtSurname = findViewById(R.id.modify_et_surname)
        modifyEtAddress = findViewById(R.id.modify_et_address)
        modifyEtEmail = findViewById(R.id.modify_et_email)
        modifyEtPassword = findViewById(R.id.modify_et_password)
        modifyEtPhone = findViewById(R.id.modify_et_phonenumber)

        btnArrowBack = findViewById(R.id.imageArrow)

        modifyBtnSave!!.setOnClickListener(this)


        btnArrowBack!!.setOnClickListener(View.OnClickListener {
            var intent = Intent(this, RestaurantsActivity::class.java)
            startActivity(intent)
        })



    }

    override fun onClick(p0: View?) {
        updateUser()
    }

    fun updateUser() {
        var name = modifyEtName?.text.toString()
        var surname = modifyEtSurname?.text.toString()
        var address = modifyEtAddress?.text.toString()
        var email = modifyEtEmail?.text.toString()
        var password = modifyEtPassword?.text.toString()
        var phone = modifyEtPhone?.text.toString()

        Thread {

            var db: ElPucheritoDB = ElPucheritoDB.getInstance(this)
            val user = db.userDao().getLoggedUser()

            if (name != "") {
                user.name = name
            }
            if (surname != "") {
                user.surname = surname
            }
            if (address != "") {
                user.address = address
            }
            if (phone != "") {
                user.phonenum = phone.toInt()
            }
            if (email != "") {
                user.email = email
            }
            if (password != "") {
                user.password = password
            }

            db.userDao().updateUser(user)

            println(user)
            startActivity(Intent(this, RestaurantsActivity::class.java))
        }.start()
    }

    override fun onBackPressed() {
        startActivity(Intent(this, RestaurantsActivity::class.java))
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}