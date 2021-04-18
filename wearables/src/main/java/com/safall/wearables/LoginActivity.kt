package com.safall.wearables

import android.content.Intent
import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : WearableActivity() {

    private lateinit var etEmail : EditText
    private lateinit var etPassword : EditText
    private lateinit var btnLogin : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        etEmail = findViewById(R.id.etemail)
        etPassword = findViewById(R.id.etpassword)
        btnLogin = findViewById(R.id.btnLogin)
        btnLogin.setOnClickListener{
            login()
        }


        // Enables Always-on
        setAmbientEnabled()
    }
    private fun login() {
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val repository = UserRepository()
                val response = repository.checkUser(email, password)
                var a = response.success
//                Log.d("response","$a")
                if (response.success == true) {
                    withContext(Dispatchers.Main) {
                        // dashboard khola
                        ServiceBuilder.token = "Bearer ${response.token}"
                        saveSharedPref(response.token!!)
                        val name = response.userData!!.user_username
                        val email = response.userData.user_email
                        val contactno = response.userData.user_contactno
                        val sharedPref = getSharedPreferences("UserDetails", MODE_PRIVATE)
                        val editor = sharedPref.edit()
                        editor.putString("name", name)
                        editor.putString("email", email)
                        editor.putString("contactno", contactno)
                        editor.apply()
                        editor.commit()
                        startActivity(
                                Intent(
                                        this@LoginActivity,
                                        DashActivity::class.java
                                )
                        )}
                    finish()
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                                this@LoginActivity,
                                "Invalid credentials",
                                Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                            this@LoginActivity,
                            ex.toString(),
                            Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
    private fun saveSharedPref(token:String) {
        val username = etEmail.text.toString()
        val password = etPassword.text.toString()
//        Log.d("token", "onBindViewHolder: " + token)
        val sharedPref = getSharedPreferences("LoginPref",
                MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("username", username)
        editor.putString("password", password)
        editor.putString("token", token)
        editor.apply()
        editor.commit()
    }
}
