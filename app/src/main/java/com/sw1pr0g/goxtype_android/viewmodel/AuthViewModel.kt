package com.sw1pr0g.goxtype_android.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sw1pr0g.goxtype_android.data.api.request.AuthRequest
import com.sw1pr0g.goxtype_android.data.api.response.BaseResponse
import com.sw1pr0g.goxtype_android.data.api.response.LogInResponse
import com.sw1pr0g.goxtype_android.repository.UserRepository
import kotlinx.coroutines.launch

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepo = UserRepository()
    val logInResult: MutableLiveData<BaseResponse<LogInResponse>> = MutableLiveData()

    fun logInUser(email: String, pwd: String) {
        logInResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {

                val loginRequest = AuthRequest(
                    password = pwd,
                    email = email
                )
                val response = userRepo.loginUser(loginRequest = loginRequest)

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

}