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
import org.json.JSONObject

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    var logInAction = true
    private val userRepo = UserRepository()
    val authResult: MutableLiveData<BaseResponse<AuthResponse>> = MutableLiveData()


    fun authUser(email: String, pwd: String) {
        authResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {

                val authRequest = AuthRequest(
                    password = pwd,
                    email = email
                )

                val response = when (logInAction) {
                    true -> {
                        userRepo.logInUser(logInRequest = authRequest)
                    }
                    false -> {
                        userRepo.signUpUser(signUpRequest = authRequest)
                    }
                }

                val responseStatus: Boolean = response?.isSuccessful == true
                // response?.code() == 200/201
                if (responseStatus) {
                    authResult.value = BaseResponse.Success(response?.body())
                } else {
                    val jsonResponse = JSONObject(response?.errorBody()!!.charStream().readText())
                    authResult.value = BaseResponse.Error(jsonResponse.getString("message"))
                }

            } catch (ex: Exception) {
                authResult.value = BaseResponse.Error(ex.message)
            }
        }
    }

}