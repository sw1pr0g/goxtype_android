package com.sw1pr0g.goxtype_android.repository

import com.sw1pr0g.goxtype_android.data.api.methods.UserApi
import com.sw1pr0g.goxtype_android.data.api.request.AuthRequest
import com.sw1pr0g.goxtype_android.data.api.response.LogInResponse
import retrofit2.Response

class UserRepository {
    suspend fun loginUser(loginRequest: AuthRequest): Response<LogInResponse>? {
        return UserApi.getApi()?.loginUser(loginRequest = loginRequest)
    }
}