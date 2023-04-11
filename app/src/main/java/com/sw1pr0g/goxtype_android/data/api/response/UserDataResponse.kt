package com.sw1pr0g.goxtype_android.data.api.response

import com.google.gson.annotations.SerializedName

data class UserDataResponse (
    @SerializedName("_id")
    var _id: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("name")
    var name: String
)