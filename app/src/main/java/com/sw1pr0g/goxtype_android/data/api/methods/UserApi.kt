package com.sw1pr0g.goxtype_android.data.api.methods

import com.sw1pr0g.goxtype_android.data.api.ApiClient
import com.sw1pr0g.goxtype_android.data.api.request.LoginRequest
import com.sw1pr0g.goxtype_android.data.api.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST("login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<LoginResponse>

    companion object {
        fun getApi(): UserApi? {
            return ApiClient.client?.create(UserApi::class.java)
        }
    }

}