package com.example.kitkite

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        lateinit var profile: ImageView
        lateinit var school: ImageView
        lateinit var event: ImageView
        lateinit var home: ImageView
        lateinit var param: ImageView
        lateinit var message: ImageView
        lateinit var create: ImageView
        profile = findViewById<ImageView>(R.id.icon1)
        school = findViewById<ImageView>(R.id.icon2)
        message = findViewById<ImageView>(R.id.icon3)
        event = findViewById<ImageView>(R.id.icon4)
        home = findViewById<ImageView>(R.id.icon5)
        param = findViewById<ImageView>(R.id.icon6)
        create = findViewById<ImageView>(R.id.icon7)
        val sharedPref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val email = sharedPref.getString("email", "")
        val pw = sharedPref.getString("pw", "")
        Log.i("mylog", "email: $email")
        Log.i("mylog", "pw: $pw")
        profile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)

        }
        create.setOnClickListener {
            val intent = Intent(this, CreatePostActivity::class.java)
            startActivity(intent)

        }
        school.setOnClickListener {
            val intent = Intent(this, SchoolActivity::class.java)
            startActivity(intent)
        }
        message.setOnClickListener {
            val intent = Intent(this, MessageActivity::class.java)
            startActivity(intent)
        }
        event.setOnClickListener {
            val intent = Intent(this, EventActivity::class.java)
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