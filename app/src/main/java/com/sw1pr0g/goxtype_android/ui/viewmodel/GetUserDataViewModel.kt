package com.sw1pr0g.goxtype_android.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sw1pr0g.goxtype_android.data.api.response.BaseResponse
import com.sw1pr0g.goxtype_android.data.api.response.getUserDataResponse.GetUserDataResponse
import com.sw1pr0g.goxtype_android.data.repository.UserRepository
import kotlinx.coroutines.launch
import org.json.JSONObject

class GetUserDataViewModel : ViewModel() {

    private val userRepo = UserRepository()
    val getUserDataResult: MutableLiveData<BaseResponse<GetUserDataResponse>> = MutableLiveData()

    fun getUserData(token: String, userId: String) {
        getUserDataResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val response = userRepo.getUserData(
                    getUserDataHeader = "Bearer $token",
                    userId = userId
                )

                val responseStatus: Boolean = response?.isSuccessful == true
                if(responseStatus) {
                    getUserDataResult.value = BaseResponse.Success(response?.body())
                } else {
                    val jsonResponse = JSONObject(response?.errorBody()!!.charStream().readText())
                    getUserDataResult.value = BaseResponse.Error(jsonResponse.getString("message"))
                }
            } catch (ex: Exception) {
                getUserDataResult.value = BaseResponse.Error(ex.message)
            }

        }
    }
}