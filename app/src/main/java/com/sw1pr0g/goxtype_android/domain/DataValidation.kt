package com.sw1pr0g.goxtype_android.domain

import android.content.Context
import android.os.Looper
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import com.sw1pr0g.goxtype_android.R
import com.sw1pr0g.goxtype_android.ui.Component
import com.sw1pr0g.goxtype_android.ui.main.MainActivity

class DataValidation(private val emailLayout: TextInputLayout,
                     private val passwordLayout: TextInputLayout) {

    fun authLogIn() : Boolean {
        var returns = true

        if (emailLayout.editText!!.text.isEmpty() && passwordLayout.editText!!.text.isEmpty()) {
            emailLayout.isErrorEnabled = true
            emailLayout.error = "Email is required"
            passwordLayout.isErrorEnabled = true
            passwordLayout.error = "Password is required"
            returns = false
        } else if (emailLayout.editText!!.text.isEmpty()) {
            emailLayout.isErrorEnabled = true
            emailLayout.error = "Email is required"
            returns = false
        } else if (passwordLayout.editText!!.text.isEmpty()) {
            passwordLayout.isErrorEnabled = true
            passwordLayout.error = "Password is required"
            returns = false
        }
        return returns
    }

    fun authOffMistakes(emailLayout: TextInputLayout,
                        passwordLayout: TextInputLayout) {
        emailLayout.isErrorEnabled = false
        passwordLayout.isErrorEnabled = false
    }

    fun authFieldsUpdate(responseCode: Int, context: Context) {
        val component = Component(context)

        when (responseCode) {
            201 -> component.newActivity(MainActivity::class.java)
            404 -> {
                authFieldsIncorrect()
                Toast.makeText(
                    context,
                    R.string.log_in_server_error,
                    Toast.LENGTH_SHORT
                ).show()
            }
            else -> {
                authFieldsIncorrect()
            }
        }
    }

    private fun authFieldsIncorrect() {
        emailLayout.isErrorEnabled = true
        emailLayout.error = "Incorrect email"
        passwordLayout.isErrorEnabled = true
        passwordLayout.error = "Incorrect password"
    }

}