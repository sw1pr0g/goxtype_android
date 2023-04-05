package com.sw1pr0g.goxtype_android.domain

import com.google.android.material.textfield.TextInputLayout

class DataValidation {

    fun authLogIn(emailLayout: TextInputLayout,
                  passwordLayout: TextInputLayout) : Boolean {
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

}