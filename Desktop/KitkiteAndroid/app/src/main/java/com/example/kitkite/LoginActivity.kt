package com.example.kitkite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.kitkite.utils.Utility
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        lateinit var login : Button
        lateinit var emailog : TextView
        lateinit var passwordlog : TextView
        lateinit var gotoregister : Button
        login=findViewById(R.id.logbutton)
        gotoregister=findViewById(R.id.button3)
        emailog=findViewById(R.id.emailog)
        passwordlog=findViewById(R.id.passwordlog)
        gotoregister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
        login.setOnClickListener {
            val queue = Volley.newRequestQueue(applicationContext)
            val url = Utility.apiUrl + "auth/login"

            val data = JSONObject()

            data.put("email", emailog.text.toString())
            data.put("password", passwordlog.text.toString())


            val stringRequest = JsonObjectRequest(
                Request.Method.POST, url, data,
                Response.Listener { response ->
                    Log.i("mylog", response.toString())
                },
                Response.ErrorListener { error ->
                    Log.i("mylog", error.message.toString())
                })

            queue.add(stringRequest)
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

    }
}