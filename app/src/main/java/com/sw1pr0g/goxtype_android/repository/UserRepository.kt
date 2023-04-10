package com.sw1pr0g.goxtype_android.repository

import com.sw1pr0g.goxtype_android.data.api.methods.UserApi
import com.sw1pr0g.goxtype_android.data.api.request.LoginRequest
import com.sw1pr0g.goxtype_android.data.api.response.LoginResponse
import retrofit2.Response

class UserRepository {
    suspend fun loginUser(loginRequest: LoginRequest): Response<LoginResponse>? {
        return UserApi.getApi()?.loginUser(loginRequest = loginRequest)
    }
}