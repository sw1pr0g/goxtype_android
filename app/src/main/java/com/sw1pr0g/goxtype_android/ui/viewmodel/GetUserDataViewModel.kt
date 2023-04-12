package com.sw1pr0g.goxtype_android.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sw1pr0g.goxtype_android.data.api.response.BaseResponse
import com.sw1pr0g.goxtype_android.data.api.response.GetUserDataResponse
import com.sw1pr0g.goxtype_android.data.repository.UserRepository
import kotlinx.coroutines.launch
import org.json.JSONObject

class GetUserDataViewModel : ViewModel() {

    private val userRepo = UserRepository()
    val getUserDataResult: MutableLiveData<GetUserDataResponse> = MutableLiveData()

    fun getUserData(token: String, userId: String) {
        authResult.value = BaseResponse.Loading()
        viewModelScope.launch {

            var response = userRepo.getUserData(
                getUserDataHeader = "Bearer $token",
                userId = userId
            )

            val responseStatus: Boolean = response?.isSuccessful == true
            if(responseStatus) {

            } else {
                val jsonResponse = JSONObject(response?.errorBody()!!.charStream().readText())
                authResult.value = BaseResponse.Error(jsonResponse.getString("message"))
            }
        }
    }
}