package com.sw1pr0g.goxtype_android.domain.validation

import android.content.Context
import android.widget.CheckBox
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout
import com.sw1pr0g.goxtype_android.ui.auth.AuthActivityCallback

class SignUpValidation(
    context: Context,
    private val emailLayout: TextInputLayout,
    private val passwordLayout: TextInputLayout,
    private val repeatPasswordLayout: TextInputLayout,
    private val acceptTermsCheckBox: CheckBox) {

    private var authActivityCallback: AuthActivityCallback? = context as AuthActivityCallback?

    fun checkEmptyFields() : Boolean {
        var returns = true

        val passwordMatches = passwordLayout.editText!!.text.toString() == repeatPasswordLayout.editText!!.text.toString()

        if (emailLayout.editText!!.text.isEmpty() && passwordLayout.editText!!.text.isEmpty() &&
            repeatPasswordLayout.editText!!.text.isEmpty()) {
            emailLayout.isErrorEnabled = true
            emailLayout.error = "Email is required"
            passwordLayout.isErrorEnabled = true
            passwordLayout.error = "Password is required"
            repeatPasswordLayout.isErrorEnabled = true
            repeatPasswordLayout.error = "Repeat password is required"
            returns = false
        } else if (emailLayout.editText!!.text.isEmpty()) {
            emailLayout.isErrorEnabled = true
            emailLayout.error = "Email is required"
            returns = false
        } else if (passwordLayout.editText!!.text.isEmpty()) {
            passwordLayout.isErrorEnabled = true
            passwordLayout.error = "Password is required"
            returns = false
        } else if (repeatPasswordLayout.editText!!.text.isEmpty()) {
            repeatPasswordLayout.isErrorEnabled = true
            repeatPasswordLayout.error = "Repeat password is required"
            returns = false
        } else if (!emailLayout.editText!!.isEmailValid()){
            emailLayout.isErrorEnabled = true
            emailLayout.error = "Email is incorrect"
            returns = false
        } else if (!passwordMatches) {
            passwordLayout.isErrorEnabled = true
            passwordLayout.error = "Passwords don't match"
            repeatPasswordLayout.isErrorEnabled = true
            repeatPasswordLayout.error = "Passwords don't match"
            returns = false
        } else {
            if (!acceptTermsCheckBox.isChecked) {
                authActivityCallback?.showErrorSnackBar("Please read and accept all our terms and conditions!")
                returns = false
            }
        }
        return returns
    }

    fun offFieldsMistakes() {
        emailLayout.isErrorEnabled = false
        passwordLayout.isErrorEnabled = false
        repeatPasswordLayout.isErrorEnabled = false
    }

    fun fieldsIncorrect() {
        emailLayout.isErrorEnabled = true
        emailLayout.error = "Incorrect email"
        passwordLayout.isErrorEnabled = true
        passwordLayout.error = "Incorrect password"
        repeatPasswordLayout.isErrorEnabled = true
        repeatPasswordLayout.error = "Incorrect repeated password"
    }

    private fun EditText.isEmailValid(): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(this.text.toString()).matches()
    }

}