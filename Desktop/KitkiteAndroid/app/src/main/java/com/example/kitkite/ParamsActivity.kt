package com.example.kitkite

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate

class ParamsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_params)
        lateinit var profile : ImageView
        lateinit var school : ImageView
        lateinit var event : ImageView
        lateinit var home : ImageView
        lateinit var param : ImageView
        lateinit var message : ImageView
        val nightModeSwitch = findViewById<Switch>(R.id.switch1)

        nightModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
        val logout = findViewById<LinearLayout>(R.id.log_out)
        logout.setOnClickListener {
            val sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()

            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
        profile=findViewById<ImageView>(R.id.icon1)
        school=findViewById<ImageView>(R.id.icon2)
        message=findViewById<ImageView>(R.id.icon3)
        event=findViewById<ImageView>(R.id.icon4)
        home=findViewById<ImageView>(R.id.icon5)
        param=findViewById<ImageView>(R.id.icon6)
        profile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)

        }
        school.setOnClickListener {
            val intent = Intent(this,SchoolActivity::class.java)
            startActivity(intent)
        }
        message.setOnClickListener {
            val intent = Intent(this, MessageActivity::class.java)
            startActivity(intent)
        }
        event.setOnClickListener {
            val intent = Intent(this,EventActivity::class.java)
            startActivity(intent)
        }
        home.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
        param.setOnClickListener {
            val intent = Intent(this, ParamsActivity::class.java)
            startActivity(intent)
        }
    }
}