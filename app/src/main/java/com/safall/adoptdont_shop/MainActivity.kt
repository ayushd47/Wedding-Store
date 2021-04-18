package com.safall.adoptdont_shop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.google.android.material.snackbar.Snackbar
import com.safall.adoptdont_shop.api.ServiceBuilder
import com.safall.adoptdont_shop.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var etEmail : EditText
    private lateinit var etPassword : EditText
    private lateinit var tvSignup : TextView
    private lateinit var btnLogin : Button
    private lateinit var linearLayout : LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide();
        setContentView(R.layout.activity_main)
        etEmail = findViewById(R.id.etemail)
        etPassword = findViewById(R.id.etpassword)
        btnLogin = findViewById(R.id.btnLogin)
        tvSignup = findViewById(R.id.tvsignup)
        linearLayout = findViewById(R.id.linearLayout)


        btnLogin.setOnClickListener{
            login()

        }

        tvSignup.setOnClickListener{
            startActivity(Intent(this@MainActivity, RegisterActivity::class.java))
        }
    }
    private fun login() {
        val user_email = etEmail.text.toString()
        val user_password = etPassword.text.toString()
//
//        var user: User?
//        CoroutineScope(Dispatchers.IO).launch {
//            user = UserDB.getInstance(this@MainActivity)
//                    .getUserDAO()
//                    .checkUser(username, password)
//            if (user == null) {
//                withContext(Dispatchers.Main) {
//                    Toast.makeText(this@MainActivity, "Invalid credentials", Toast.LENGTH_SHORT)
//                            .show()
//                }
//            } else {
//                saveSharedPref()
//                startActivity(Intent(this@MainActivity,
//                        DashboardActivity::class.java))
//            }
//        }
        Log.d("username pass", "$user_email -- $user_password")

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val repository = UserRepository()
                val response = repository.checkUser(user_email, user_password)
                var a = response.success
//                Log.d("response","$a")
                if (response.success == true) {
                    withContext(Dispatchers.Main) {
                    // dashboard khola
                    ServiceBuilder.token = "Bearer ${response.token}"
                    saveSharedPref(response.token!!)
                        Log.d("Data", "onBindViewHolder: " + response.data)
//                    val name = response.userData!!
//                    val email = response.userData.user_email
//                    val contactno = response.userData.user_contactno
//                    val sharedPref = getSharedPreferences("UserDetails",MODE_PRIVATE)
//                    val editor = sharedPref.edit()
//                    editor.putString("name", name)
//                    editor.putString("email", email)
//                    editor.putString("contactno", contactno)
//                    editor.apply()
//                    editor.commit()
                    startActivity(
                        Intent(
                            this@MainActivity,
                            DashboardActivity::class.java
                        )
                    )}
                    finish()
                } else {
                    withContext(Dispatchers.Main) {
                        val snack =
                            Snackbar.make(
                                linearLayout,
                                "Invalid credentials",
                                Snackbar.LENGTH_LONG
                            )
                        snack.setAction("OK", View.OnClickListener {
                            snack.dismiss()
                        })
                        snack.show()
                    }
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@MainActivity,
                        ex.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    }

//    private fun saveSharedPref() {
//        val email = etEmail.text.toString()
//        val password = etPassword.text.toString()
//        val sharedPref = getSharedPreferences("MyPref",
//                MODE_PRIVATE)
//        val editor = sharedPref.edit()
//        editor.putString("email", email)
//        editor.putString("password", password)
//        editor.apply()
//    }

//    private fun getSharedPref() {
//        val sharedPref = getSharedPreferences("MyPref", MODE_PRIVATE)
//        val email = sharedPref.getString("email", "")
//        val password = sharedPref.getString("password", "")
//        Toast.makeText(this, "Username : $email and password : $password", Toast.LENGTH_SHORT)
//                .show()
//    }


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