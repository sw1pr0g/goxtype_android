package com.sw1pr0g.goxtype_android.domain

import com.sw1pr0g.goxtype_android.data.api.ApiInterface
import com.sw1pr0g.goxtype_android.data.api.LogInBody
import com.sw1pr0g.goxtype_android.data.api.RetrofitInstance
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserAuthAction {
    fun logIn(email: String, password: String): Int {
        var responseCode = 0

        val retIn = RetrofitInstance.getRetrofitInstance().create(ApiInterface::class.java)
        val logInInfo = LogInBody(email, password)

        retIn.logIn(logInInfo).enqueue(object : Callback<ResponseBody> {

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

                val onFailureResponse = 99

            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                responseCode = response.code()
            }



        })
        return responseCode
    }
}