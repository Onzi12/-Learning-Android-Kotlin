package com.example.firstrealapp

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.firstrealapp.databinding.ActivityLoginBinding
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {

    // private lateinit var blogPreferences: BlogPreferences
    // Lazy example. Lazy properties are initialized on the first usage (here, it's in the onCreate method)
    private val blogPreferences: BlogPreferences by lazy { BlogPreferences(this) }

    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // bypass Login Activity if the user is logged-in.
        if (blogPreferences.isLoggedIn()) {
            startMainActivity()
            return
        }

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener { onLoginClicked() }

        // two ways to achieve the same
        binding.textUsernameLayout.editText?.addTextChangedListener { onTextChanged(binding.textUsernameLayout) }
        binding.passwordInput.addTextChangedListener{ onTextChanged(binding.textPasswordLayout) }
    }



    private fun onTextChanged(inputLayout: TextInputLayout) {
        if (inputLayout.editText?.text?.isEmpty() != true)
            inputLayout.error = null
    }

    private fun onLoginClicked() {

        val username: String = binding.textUsernameLayout.editText?.text.toString()
        val password: String = binding.textPasswordLayout.editText?.text.toString()

        if(username.isEmpty() || password.isEmpty()) {
            // UX: the user will see the error on the activity. (the spacing between the views will slightly move)
            // UX: You can put a subtle message, if you set the error on the input. The user will see the error only if he's focused on the view.
            if (username.isEmpty())
                binding.textUsernameLayout.error = "Please fill the username"
            if (password.isEmpty())
                binding.textPasswordLayout.error = "Please fill the password"
        }
        else if (username != "admin" && password != "admin")
            onWrongCredentials()
        else
            performLogin()
    }

    private fun performLogin() {
        blogPreferences.setLoginState(true)

        binding.textUsernameLayout.isEnabled = false
        binding.textPasswordLayout.isEnabled = false
        binding.loginButton.visibility = View.INVISIBLE
        binding.loadingProgress.visibility = View.VISIBLE

        Handler(mainLooper).postDelayed({ startMainActivity() }, 2000)
    }

    private fun startMainActivity() {
        val intent = Intent(this, DetailsActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun onWrongCredentials() {
        AlertDialog.Builder(this)
            .setTitle("Login Failed")
            .setMessage("Username or password is incorrect. Please try Again.")
            .setPositiveButton("OK") { dialog, which -> if(which == DialogInterface.BUTTON_POSITIVE) dialog.dismiss() }
            .show()
    }
}