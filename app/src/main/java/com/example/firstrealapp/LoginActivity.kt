package com.example.firstrealapp

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButton.setOnClickListener { onLoginClicked() }

        // two ways to achieve the same
        textUsernameLayout.editText?.addTextChangedListener { onTextChanged(textUsernameLayout) }
        passwordInput.addTextChangedListener{ onTextChanged(textPasswordLayout) }
    }

    private fun onTextChanged(inputLayout: TextInputLayout) {
        if (inputLayout.editText?.text?.isEmpty() != true)
            inputLayout.error = null
    }

    private fun onLoginClicked() {

        val username: String = this.textUsernameLayout.editText?.text.toString()
        val password: String = this.textPasswordLayout.editText?.text.toString()

        if(username.isEmpty() || password.isEmpty()) {
            // UX: the user will see the error on the activity. (the spacing between the views will slightly move)
            // UX: You can put a subtle message, if you set the error on the input. The user will see the error only if he's focused on the view.
            if (username.isEmpty())
                this.textUsernameLayout.error = "Please fill the username"
            if (password.isEmpty())
                this.textPasswordLayout.error = "Please fill the password"
        }
        else if (username != "admin" && password != "admin")
            onWrongCredentials()
        else
            performLogin()
    }

    private fun performLogin() {
        textUsernameLayout.isEnabled = false
        textPasswordLayout.isEnabled = false
        loginButton.visibility = View.INVISIBLE
        loadingProgress.visibility = View.VISIBLE

        Handler().postDelayed({
            startMainActivity()
            finish() }, 2000)
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun onWrongCredentials() {
        AlertDialog.Builder(this)
            .setTitle("Login Failed")
            .setMessage("Username or password is incorrect. Please try Again.")
            .setPositiveButton("OK") { dialog, which -> if(which == DialogInterface.BUTTON_POSITIVE) dialog.dismiss() }
            .show()
    }
}