package com.example.kitkite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.kitkite.utils.Utility
import org.json.JSONObject
import java.nio.charset.Charset

class RegisterSchoolActivity : AppCompatActivity() {
    lateinit var name :EditText

    lateinit var email :EditText
    lateinit var password :EditText
    lateinit var confirmPassword :EditText
    lateinit var register :Button
    lateinit var gotologin :Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_school)
        name=findViewById(R.id.schoolname)
        email=findViewById(R.id.email)
        password=findViewById(R.id.password)
        confirmPassword=findViewById(R.id.confirmpassword)
        register=findViewById(R.id.Registerbutton)
        gotologin=findViewById(R.id.gotologin)
        register.setOnClickListener {
            val queue = Volley.newRequestQueue(applicationContext)
            val url = Utility.apiUrl + "auth/signupSchool"

            val data = JSONObject()
            data.put("name", name.text.toString())

            data.put("email", email.text.toString())
            data.put("password", password.text.toString())
            data.put("confirmPassword", confirmPassword.text.toString())

            val stringRequest = JsonObjectRequest(Request.Method.POST, url, data,
                Response.Listener { response ->
                    Log.i("mylog", response.toString())
                },
                Response.ErrorListener { error ->
                    Log.i("mylog", error.message.toString())
                })

            queue.add(stringRequest)
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        gotologin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }
}