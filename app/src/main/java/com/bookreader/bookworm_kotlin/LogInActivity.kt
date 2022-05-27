package com.bookreader.bookworm_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class LogInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        // widgets
        val username = findViewById<TextView>(R.id.username_log_in)
        val password = findViewById<TextView>(R.id.password_log_in)
        val sign_in_button = findViewById<Button>(R.id.sign_in)

        // database
        val db = DataBaseHelper(this, "login.db", null, 1)


        sign_in_button.setOnClickListener {
            val username = username.text.toString()
            val password = password.text.toString()

            if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password))
                Toast.makeText(this@LogInActivity, "All fields are required!", Toast.LENGTH_SHORT).show()
            else{
                if(db.checkUsernamePassword(username, password)){
                    Toast.makeText(this@LogInActivity, "Log in successful!", Toast.LENGTH_SHORT).show()

                    // changing the screen to book search
                    val intent = Intent(applicationContext, SearchActivity::class.java)
                    startActivity(intent)
                }
                else
                    Toast.makeText(this@LogInActivity, "Log in failed!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}