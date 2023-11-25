package com.example.zodicsignapp


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class RegistrationActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        // Initialize Firebase Authentication
        auth = FirebaseAuth.getInstance()

        val etEmail = findViewById<EditText>(R.id.Email)
        val etPassword = findViewById<EditText>(R.id.Password)
        val btnRegister = findViewById<Button>(R.id.btnRegister)

        btnRegister.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            // Call the Firebase authentication method
            registrationWithEmailAndPassword(email, password)

            // After registering, navigate to the next page (replace NextActivity::class.java with the actual class of your next activity)

        }



    }
    private fun registrationWithEmailAndPassword(email:String,  password:String){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnSuccessListener {
            Toast.makeText(applicationContext,"Registered Successfylly",Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }.addOnFailureListener {
            Toast.makeText(applicationContext,it.message,Toast.LENGTH_SHORT).show()

        }

    }
    }




