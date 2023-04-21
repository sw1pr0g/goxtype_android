package com.sw1pr0g.goxtype_android.utils

import android.content.Context
import android.content.SharedPreferences
import com.auth0.android.jwt.JWT
import com.sw1pr0g.goxtype_android.R
import java.util.Calendar

object SessionManager {

    private const val USER_TOKEN = "user_token"
    private const val USER_EMAIL = "user_email"
    private const val USER_NAME = "user_name"

    fun getIdFromToken(context: Context): String {
        val currentToken = getToken(context).toString()
        val jwtToken = JWT(currentToken)
        jwtToken.issuer
        return jwtToken.getClaim("id").asString().toString()
    }
    /**
     * Function to save user token
     */
    fun saveAuthToken(context: Context, token: String) {
        saveString(context, USER_TOKEN, token)
    }

    // TODO("Add saving user data to shared preferences")
    fun saveUserEmail(context: Context, email: String) {
        saveString(context, USER_EMAIL, email)
    }

    fun saveUserName(context: Context, pwd: String) {
        saveString(context, USER_NAME, pwd)
    }

    /**
     * Function to fetch auth token
     */
    fun getToken(context: Context): String? {
        return getString(context, USER_TOKEN)
    }

    fun getUserEmail(context: Context): String? {
        return getString(context, USER_EMAIL)
    }

    fun getUserName(context: Context): String? {
        return getString(context, USER_NAME)
    }

    private fun saveString(context: Context, key: String, value: String) {
        val prefs: SharedPreferences =
            context.getSharedPreferences(context.getString(R.string.app_name),
            Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString(key, value)
        editor.apply()

    }

    private fun getString(context: Context, key: String): String? {
        val prefs: SharedPreferences =
            context.getSharedPreferences(context.getString(R.string.app_name),
            Context.MODE_PRIVATE)
        return prefs.getString(key, null)
    }

    fun clearData(context: Context) {
        val editor =
            context.getSharedPreferences(context.getString(R.string.app_name),
            Context.MODE_PRIVATE).edit()
        editor.clear()
        editor.apply()
    }

    fun getGreetingMessage(): String {
        val c = Calendar.getInstance()

        return when (c.get(Calendar.HOUR_OF_DAY)) {
            in 0..5 -> "Good night"
            in 6..12 -> "Good morning"
            in 13..18 -> "Good afternoon"
            in 19..23 -> "Good evening"
            else -> "Good morning"
        }
    }


}