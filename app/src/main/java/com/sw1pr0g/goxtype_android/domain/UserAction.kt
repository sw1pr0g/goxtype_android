package com.sw1pr0g.goxtype_android.domain

import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.sw1pr0g.goxtype_android.data.api.ApiInterface
import com.sw1pr0g.goxtype_android.data.api.LogInBody
import com.sw1pr0g.goxtype_android.data.api.RetrofitInstance
import com.sw1pr0g.goxtype_android.ui.main.MainActivity
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserAction {
    private fun logIn(email: String, password: String) {

        val retIn = RetrofitInstance.getRetrofitInstance().create(ApiInterface::class.java)
        val logInInfo = LogInBody(email, password)

        retIn.logIn(logInInfo).enqueue(object : Callback<ResponseBody> {

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                
                if (response.isSuccessful) {
                    val intent = Intent(activity, MainActivity::class.java)
                    startActivity(intent)
                    activity?.finish()
                } else {
                    logInEmailTextLayout.isErrorEnabled = true
                    logInEmailTextLayout.error = "Incorrect email"
                    logInPasswordTextLayout.isErrorEnabled = true
                    logInPasswordTextLayout.error = "Incorrect password"
                    validateChecks = false
                }
            }

        })
        Thread.sleep(500)
        dialogAuthLoading.dismissDialog()
    }
}