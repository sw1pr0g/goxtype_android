package com.sw1pr0g.goxtype_android.ui.auth

import com.sw1pr0g.goxtype_android.data.api.response.AuthResponse
import com.sw1pr0g.goxtype_android.data.api.response.GetUserDataResponse

interface AuthActivityCallback {

    fun processAuth(authResponse: AuthResponse?, getUserDataResponse: GetUserDataResponse?)

    // fun processSignUp(data: SignUpResponse?)

    fun showErrorSnackBar(message: String?)

}