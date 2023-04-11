package com.sw1pr0g.goxtype_android.repository

import com.sw1pr0g.goxtype_android.data.api.methods.UserApi
import com.sw1pr0g.goxtype_android.data.api.request.AuthRequest
import com.sw1pr0g.goxtype_android.data.api.response.AuthResponse
import retrofit2.Response

class UserRepository {
    suspend fun logInUser(logInRequest: AuthRequest): Response<AuthResponse>? {
        return UserApi.getApi()?.logInUser(logInRequest = logInRequest)
    }
    suspend fun signUpUser(signUpRequest: AuthRequest): Response<AuthResponse>? {
        return UserApi.getApi()?.signUpUser(signUpRequest = signUpRequest)
    }
}