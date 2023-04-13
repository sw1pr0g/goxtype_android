package com.sw1pr0g.goxtype_android.ui.auth

import com.sw1pr0g.goxtype_android.data.api.response.AuthResponse
import com.sw1pr0g.goxtype_android.data.api.response.getUserDataResponse.GetUserDataResponse

interface AuthActivityCallback {

    fun processAuth(authResponse: AuthResponse?)

    fun processGetUserData(getUserDataResponse: GetUserDataResponse?)

    fun showErrorSnackBar(message: String?)

}