package com.sw1pr0g.goxtype_android.data.api.methods

import com.sw1pr0g.goxtype_android.data.api.ApiClient
import com.sw1pr0g.goxtype_android.data.api.request.AuthRequest
import com.sw1pr0g.goxtype_android.data.api.response.AuthResponse
import com.sw1pr0g.goxtype_android.data.api.response.GetUserDataResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApi {

    @POST("login")
    suspend fun logInUser(@Body logInRequest: AuthRequest): Response<AuthResponse>

    @POST("signup")
    suspend fun signUpUser(@Body signUpRequest: AuthRequest): Response<AuthResponse>

    @GET("users/{id}")
    suspend fun getUserData(@Header("Authorization")
                                getUserDataHeader: String,
                            @Path("id")
                                userId: String): Response<GetUserDataResponse>

    companion object {
        fun getApi(): UserApi? {
            return ApiClient.client?.create(UserApi::class.java)
        }
    }

}