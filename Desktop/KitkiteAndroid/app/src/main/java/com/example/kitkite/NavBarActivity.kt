package com.example.kitkite
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity


class NavBarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav_bar)

        val profile = findViewById<ImageView>(R.id.icon1)
        val school = findViewById<ImageView>(R.id.icon2)
        val message = findViewById<ImageView>(R.id.icon3)
        val event = findViewById<ImageView>(R.id.icon4)
        val home = findViewById<ImageView>(R.id.icon5)
        val param = findViewById<ImageView>(R.id.icon6)
        fun createIntent(imageView: ImageView): Intent {
            return when (imageView) {
                profile -> Intent(this, ProfileActivity::class.java)
                school -> Intent(this, SchoolActivity::class.java)
                message -> Intent(this, MessageActivity::class.java)
                event -> Intent(this, EventActivity::class.java)
                home -> Intent(this, HomeActivity::class.java)
                param -> Intent(this, ParamsActivity::class.java)
                else -> throw IllegalArgumentException("Invalid ImageView")
            }
        }
        profile.setOnClickListener {
            startActivity(createIntent(profile))
        }

        school.setOnClickListener {
            startActivity(createIntent(school))
        }

        message.setOnClickListener {
            startActivity(createIntent(message))
        }

        event.setOnClickListener {
            startActivity(createIntent(event))
        }

        home.setOnClickListener {
            startActivity(createIntent(home))
        }

        param.setOnClickListener {
            startActivity(createIntent(param))
        }
    }
}
