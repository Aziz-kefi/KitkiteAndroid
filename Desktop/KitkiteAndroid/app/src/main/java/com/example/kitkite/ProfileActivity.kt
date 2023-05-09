package com.example.kitkite
import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.kitkite.models.User
import com.example.kitkite.models.UserResponse
import com.example.kitkite.utils.Utility
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class ProfileActivity : AppCompatActivity() {
    private val PICK_IMAGE_REQUEST = 1 // Request code for selecting an image
    private lateinit var selectedImageUri: Uri // URI of the selected image

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val sharedPref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val userId = sharedPref.getString("userId", "")
        val token = sharedPref.getString("token", "")
        val firstName = findViewById<TextView>(R.id.first_name)
        val lastName = findViewById<TextView>(R.id.last_name)
        val email = findViewById<TextView>(R.id.email)
        val profileImage = findViewById<CircleImageView>(R.id.profile_image)
        val choose= findViewById<Button>(R.id.choosebutton)
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

choose.setOnClickListener{
    // Create an Intent to pick an image from the gallery
    val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
    startActivityForResult(intent, PICK_IMAGE_REQUEST)
}



        val queue = Volley.newRequestQueue(applicationContext)
        val url = Utility.apiUrl + "users/"+userId
        val stringRequest = object : StringRequest(
            Request.Method.GET, url,
            Response.Listener { response ->
                val userResponse = Gson().fromJson(response, UserResponse::class.java)
                val user = userResponse.user
                Log.d("User", "ID: ${user._id}, First Name: ${user.firstname}, Last Name: ${user.lastname},URL:${user.imageUrl} , Email: ${user.email}")
                runOnUiThread {
                    firstName.text = user.firstname
                    lastName.text= user.lastname
                    email.text=user.email
                    Picasso.get().load(user.imageUrl)
                        .placeholder(R.drawable.avatar) // optional placeholder image
                        .error(R.drawable.error) // optional error image
                        .into(profileImage);
                }

                Log.d("Response", response)
                Log.i("mylog",response)

            },
            Response.ErrorListener { error ->
                // Handle error
                Log.e("Error", error.toString())
            }
        )
        {
            @Throws(AuthFailureError::class)
              override  fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers["Authorization"] = "Bearer $token"
                return headers
            }
        }

// Add the request to the RequestQueue.
        queue.add(stringRequest)

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Check if the result is for selecting an image and if the operation was successful
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            // Get the URI of the selected image
            selectedImageUri = data.data!!

            // Set the selected image in the ImageView
            val profileImage = findViewById<CircleImageView>(R.id.profile_image)
            profileImage.setImageURI(selectedImageUri)
        }
    }
}