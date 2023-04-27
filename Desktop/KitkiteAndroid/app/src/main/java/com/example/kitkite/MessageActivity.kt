package com.example.kitkite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast

class MessageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)
        lateinit var profile : ImageView
        lateinit var school : ImageView
        lateinit var event : ImageView
        lateinit var home : ImageView
        lateinit var param : ImageView
        lateinit var message : ImageView
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