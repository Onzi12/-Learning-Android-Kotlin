package com.example.firstrealapp

import android.content.Context
import android.content.SharedPreferences

private const val DB_NAME = "travel-blog"
private const val KEY_LOGIN_STATE = "key_login_state"

class BlogPreferences(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(DB_NAME, Context.MODE_PRIVATE)

    fun isLoggedIn(): Boolean = sharedPreferences.getBoolean(KEY_LOGIN_STATE, false)

    fun setLoginState(isLoggedIn: Boolean) {
        sharedPreferences.edit().putBoolean(KEY_LOGIN_STATE, isLoggedIn).apply()
    }
}