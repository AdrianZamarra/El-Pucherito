package com.ttt.elpucherito.activities.users

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.ttt.elpucherito.R
import com.ttt.elpucherito.db.ElPucheritoDB

class ModifyProfile : AppCompatActivity(), View.OnClickListener {

    private var modifyTvmodify : TextView? = null
    private var modifyEtEmail : EditText? = null
    private var modifyEtAddress : EditText? = null
    private var modifyEtPassword : EditText? = null
    private var modifyBtnSave : Button? = null

    private var modifyEtName : EditText? = null
    private var modifyEtSurname : EditText? = null
    private var modifyEtPhone : EditText? = null

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

        modifyBtnSave!!.setOnClickListener(this)

        private fun updateUser(){
            val name = modifyEtName?.text.toString()
            val surname = modifyEtSurname?.text.toString()
            val address = modifyEtAddress?.text.toString()
            val email = modifyEtEmail?.text.toString()
            val password = modifyEtPassword?.text.toString()
            val phone = modifyEtPhone?.text.toString().toInt()

            Thread{

                var db: ElPucheritoDB = ElPucheritoDB.getInstance(this)
                val user = db.userDao().getLoggedUser()

                user.name = name
                user.surname = surname
                user.address = address
                user.phonenum = phone
                user.email = email
                user.password = password

                db.userDao().updateUser(user)

            }.start()
        }
    }

    override fun onClick(p0: View?) {
        updateUser()
    }



}