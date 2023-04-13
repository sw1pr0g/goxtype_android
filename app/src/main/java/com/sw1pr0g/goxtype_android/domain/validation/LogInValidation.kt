package com.sw1pr0g.goxtype_android.domain.validation

import com.google.android.material.textfield.TextInputLayout

class LogInValidation(private val emailLayout: TextInputLayout,
                      private val passwordLayout: TextInputLayout) {

    fun checkEmptyFields() : Boolean {
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

    fun offFieldsMistakes() {
        emailLayout.isErrorEnabled = false
        passwordLayout.isErrorEnabled = false
    }

    fun fieldsIncorrect() {
        emailLayout.isErrorEnabled = true
        emailLayout.error = "Incorrect email"
        passwordLayout.isErrorEnabled = true
        passwordLayout.error = "Incorrect password"
    }

}