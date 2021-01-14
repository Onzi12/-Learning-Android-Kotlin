package com.example.firstrealapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //val helloWorld = findViewById<TextView>(R.id.helloWorldTextView)
        //helloWorld.text = "Boom, changed."
        }

    override fun onDestroy() {
        super.onDestroy()

        // something.
    }
}