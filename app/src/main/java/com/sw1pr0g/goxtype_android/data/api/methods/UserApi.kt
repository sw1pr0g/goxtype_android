package com.sw1pr0g.goxtype_android.data.api.methods

import com.sw1pr0g.goxtype_android.data.api.ApiClient
import com.sw1pr0g.goxtype_android.data.api.request.AuthRequest
import com.sw1pr0g.goxtype_android.data.api.response.LogInResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST("login")
    suspend fun loginUser(@Body loginRequest: AuthRequest): Response<LogInResponse>

    companion object {
        fun getApi(): UserApi? {
            return ApiClient.client?.create(UserApi::class.java)
        }
    }

}