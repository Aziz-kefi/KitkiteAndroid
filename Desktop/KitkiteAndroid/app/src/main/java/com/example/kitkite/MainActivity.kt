package com.example.kitkite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.coroutines.delay
import java.util.*
import kotlin.concurrent.schedule



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lateinit var gotologinn : Button
        lateinit var gotoschool: Button
        lateinit var gotoregister :Button
        gotoschool=findViewById(R.id.gotoregisterschool)
        gotologinn=findViewById(R.id.gotologinn)
        gotoregister=findViewById(R.id.gotoregister)
        gotologinn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        gotoregister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
        gotoschool.setOnClickListener{
            val intent = Intent(this, RegisterSchoolActivity::class.java)
            startActivity(intent)

        }
    }
}