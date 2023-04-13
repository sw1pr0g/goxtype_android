package com.sw1pr0g.goxtype_android.data.api.response

import com.google.gson.annotations.SerializedName

data class AuthResponse (
    @SerializedName("token")
    var token: String,
    @SerializedName("message")
    var message: String
)