package com.bookreader.bookworm_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.Display
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

/**
 * username: test
 * password: 1234
 */

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // widgets
        val username = findViewById<TextView>(R.id.username_sign_up)
        val email = findViewById<TextView>(R.id.email)
        val password = findViewById<TextView>(R.id.password_sign_up)
        val repassword = findViewById<TextView>(R.id.repassword)
        val sign_up_button = findViewById<Button>(R.id.sign_up)
        val log_in_transfer_button = findViewById<Button>(R.id.log_in)

        // database
        val db = DataBaseHelper(this, "login.db", null, 1)

        sign_up_button.setOnClickListener {
            val user = username.text.toString()
            val pass = password.text.toString()
            val repass = repassword.text.toString()
            val email = email.text.toString()

            if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(repass) || TextUtils.isEmpty(email))
                Toast.makeText(this@MainActivity, "All fields are required!", Toast.LENGTH_SHORT).show()
            else {
                if(pass == repass)
                {
                    if(!db.checkUsername(user) && !db.checkEmail(email)){
                        if(db.insertData(user, email, pass)) {
                            Toast.makeText(
                                this@MainActivity,
                                "Registration successful!",
                                Toast.LENGTH_SHORT
                            ).show()

                            // changing the screen to book search
                            val intent = Intent(applicationContext, SearchActivity::class.java)
                            startActivity(intent)
                        }
                        else
                            Toast.makeText(this@MainActivity, "Registration failed!", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        if(db.checkUsername(user))
                            Toast.makeText(this@MainActivity, "Username already exists!", Toast.LENGTH_SHORT).show()
                        if(db.checkEmail(email))
                            Toast.makeText(this@MainActivity, "E-mail already exists!", Toast.LENGTH_SHORT).show()
                        else
                            Toast.makeText(this@MainActivity, "Username and e-mail already exists!", Toast.LENGTH_SHORT).show()
                    }
                }
                else
                    Toast.makeText(this@MainActivity, "Password doesn't match!", Toast.LENGTH_SHORT).show()
            }
        }

        log_in_transfer_button.setOnClickListener {

            // changing the screen to log in
            val intent = Intent(applicationContext, LogInActivity::class.java)
            startActivity(intent)
        }
    }
}