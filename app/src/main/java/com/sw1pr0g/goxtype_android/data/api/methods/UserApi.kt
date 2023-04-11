package com.sw1pr0g.goxtype_android.data.api.methods

import com.sw1pr0g.goxtype_android.data.api.ApiClient
import com.sw1pr0g.goxtype_android.data.api.request.AuthRequest
import com.sw1pr0g.goxtype_android.data.api.response.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST("login")
    suspend fun logInUser(@Body logInRequest: AuthRequest): Response<AuthResponse>

    @POST("signup")
    suspend fun signUpUser(@Body signUpRequest: AuthRequest): Response<AuthResponse>

    companion object {
        fun getApi(): UserApi? {
            return ApiClient.client?.create(UserApi::class.java)
        }
    }

}