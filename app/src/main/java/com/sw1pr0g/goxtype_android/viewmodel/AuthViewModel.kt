package com.sw1pr0g.goxtype_android.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sw1pr0g.goxtype_android.data.api.request.AuthRequest
import com.sw1pr0g.goxtype_android.data.api.response.BaseResponse
import com.sw1pr0g.goxtype_android.data.api.response.AuthResponse
import com.sw1pr0g.goxtype_android.repository.UserRepository
import kotlinx.coroutines.launch
import kotlin.math.sign

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepo = UserRepository()
    val logInResult: MutableLiveData<BaseResponse<AuthResponse>> = MutableLiveData()
    val signUpResult: MutableLiveData<BaseResponse<AuthResponse>> = MutableLiveData()


    fun logInUser(email: String, pwd: String) {
        logInResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {

                val logInRequest = AuthRequest(
                    password = pwd,
                    email = email
                )
                val response = userRepo.logInUser(logInRequest = logInRequest)

                val responseStatus: Boolean = response?.isSuccessful == true
                // response?.code() == 200/201
                if (responseStatus) {
                    logInResult.value = BaseResponse.Success(response?.body())
                } else {
                    logInResult.value = BaseResponse.Error(response?.message())
                }

            } catch (ex: Exception) {
                logInResult.value = BaseResponse.Error(ex.message)
            }
        }
    }

    fun signUpUser(email: String, pwd: String) {
        signUpResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {

                val signUpRequest = AuthRequest(
                    password = pwd,
                    email = email
                )
                val response = userRepo.signUpUser(signUpRequest = signUpRequest)

                val responseStatus: Boolean = response?.isSuccessful == true
                // response?.code() == 200/201
                if (responseStatus) {
                    signUpResult.value = BaseResponse.Success(response?.body())
                } else {
                    signUpResult.value = BaseResponse.Error(response?.message())
                }

            } catch (ex: Exception) {
                signUpResult.value = BaseResponse.Error(ex.message)
            }
        }
    }

}