package com.sw1pr0g.goxtype_android.utils

import android.content.Context
import android.content.SharedPreferences
import com.auth0.android.jwt.JWT
import com.sw1pr0g.goxtype_android.R

object SessionManager {

    private const val USER_TOKEN = "user_token"
    private const val USER_EMAIL = "user_token"
    private const val USER_NAME = "user_token"

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

}