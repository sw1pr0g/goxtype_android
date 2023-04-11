package com.sw1pr0g.goxtype_android.ui.auth

import com.sw1pr0g.goxtype_android.data.api.response.AuthResponse

interface AuthActivityCallback {

    fun processAuth(data: AuthResponse?)

    // fun processSignUp(data: SignUpResponse?)

    fun showErrorSnackBar(message: String?)

}