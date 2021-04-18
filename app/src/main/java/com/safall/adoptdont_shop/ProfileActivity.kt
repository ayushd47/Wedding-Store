package com.safall.adoptdont_shop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ProfileActivity : AppCompatActivity() {
    private lateinit var tvUsername: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvContactno: TextView
    private lateinit var btnUpdateLink: Button

    var name : String=""
    var email: String=""
    var contactno : String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        tvUsername = findViewById(R.id.tvusername)
        tvEmail = findViewById(R.id.tvemail)
        tvContactno = findViewById(R.id.tvcontactno)
        btnUpdateLink = findViewById(R.id.btnUpdateLink)
        getProfile()

        btnUpdateLink.setOnClickListener {
            Intent(this, UpdateUserActivity::class.java).also {
                startActivity(it)
            }
        }

        tvUsername.setText("Username: "+name)
        tvEmail.setText("Email: "+email)
        tvContactno.setText("Contact no: "+contactno)

    }

    private fun getProfile() {
        val sharedPref = getSharedPreferences("UserDetails", MODE_PRIVATE)
        name = sharedPref.getString("name", "")!!
        email = sharedPref.getString("email", "")!!
        contactno= sharedPref.getString("contactno", "")!!
    }
}