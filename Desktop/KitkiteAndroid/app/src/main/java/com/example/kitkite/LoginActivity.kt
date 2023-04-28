package com.example.kitkite

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
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
                    val token = response.getString("token")
                    val userId = response.getJSONObject("user").getString("_id")
                    val sharedPref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                    with (sharedPref.edit()) {
                        putString("token", token)
                        putString("userId", userId)
                        apply()
                    }
                    Log.i("mylog", "Stored token: $token")
                    Log.i("mylog", "UserId: $userId")
                        val intent = Intent(this,HomeActivity::class.java)
                        startActivity(intent)

                },
                Response.ErrorListener { error ->
                    val response = JSONObject(error.networkResponse.data.toString(Charsets.UTF_8))
                    val errorMessage = response.getJSONArray("data").getJSONObject(0).getString("msg")
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Login Error")
                    builder.setMessage(errorMessage)
                    builder.setPositiveButton("OK", null)
                    val dialog = builder.create()
                    dialog.show()
                })

            queue.add(stringRequest)
        }

    }
}