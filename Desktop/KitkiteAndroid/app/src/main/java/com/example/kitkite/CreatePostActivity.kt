package com.example.kitkite

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore

import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.kitkite.utils.Utility
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.asRequestBody
import okio.*
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException

import kotlin.io.use

class CreatePostActivity : AppCompatActivity() {
    private val PICK_IMAGE_REQUEST = 1 // Request code for selecting an image
    private lateinit var selectedImageUri: Uri // URI of the selected image

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)
        val sharedPref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val userId = sharedPref.getString("userId", "")
        val token = sharedPref.getString("token", "")

        // Get references to the UI elements
        val choosePhotoButton = findViewById<Button>(R.id.choosePhotoButton)
        val createPostButton = findViewById<Button>(R.id.createPostButton)
        val descriptionEditText = findViewById<EditText>(R.id.descriptionEditText)
        val imageView = findViewById<ImageView>(R.id.imageView)

        // Set an OnClickListener on the Choose Photo button
        choosePhotoButton.setOnClickListener {
            // Create an Intent to pick an image from the gallery
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
        }

        // Set an OnClickListener on the Create Post button
        createPostButton.setOnClickListener {
            createPost()

        }
    }

    private fun createPost() {
        val sharedPref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val userId = sharedPref.getString("userId", "")
        val token = sharedPref.getString("token", "")

        if (selectedImageUri == null) {
            Toast.makeText(applicationContext, "Please select an image", Toast.LENGTH_SHORT).show()
            return
        }

        val description = findViewById<EditText>(R.id.descriptionEditText).text.toString()

        val imageByteArray = getByteArrayFromUri(selectedImageUri)
        val file = File.createTempFile("img", ".png", cacheDir)
        file.writeBytes(imageByteArray)

        val requestFile: RequestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
        Log.i("mylog", selectedImageUri.toString())
        Log.i("mylog", imageByteArray.toString())
        val requestBody: MultipartBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("image", file.name, requestFile)
            .addFormDataPart("user", userId.toString())
            .addFormDataPart("content", description)
            .build()

        val request: Request = Request.Builder()
            .url(Utility.apiUrl + "posts/")
            .header("Authorization", "Bearer $token")
            .post(requestBody)
            .build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body?.string()


                if (response.isSuccessful) {
                    runOnUiThread {
                        Toast.makeText(applicationContext, "Post created successfully", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                } else {
                    runOnUiThread {
                        Toast.makeText(applicationContext, "Error creating post", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                // Handle error here if needed
                runOnUiThread {
                    Toast.makeText(applicationContext, "Error creating post", Toast.LENGTH_SHORT).show()
                }
            }
        })

    }


    private fun getByteArrayFromUri(uri: Uri): ByteArray {
        val inputStream = contentResolver.openInputStream(uri)

        val sink: BufferedSink = ByteArrayOutputStream().sink().buffer()
        inputStream?.use { source -> sink.writeAll(source.source()) }

        return sink.buffer().readByteArray()
    }


    // This method is called when an activity that was started with startActivityForResult() finishes
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Check if the result is for selecting an image and if the operation was successful
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            // Get the content:// URI of the selected image
            selectedImageUri = data.data!!

            // Set the selected image in the ImageView
            val imageView = findViewById<ImageView>(R.id.imageView)
            imageView.setImageURI(selectedImageUri)
        }
    }

}