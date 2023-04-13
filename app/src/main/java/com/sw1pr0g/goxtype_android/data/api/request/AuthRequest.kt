package com.sw1pr0g.goxtype_android.data.api.request

import com.google.gson.annotations.SerializedName

data class AuthRequest (
    @SerializedName("email")
    var email: String,
    @SerializedName("password")
    var password: String
)