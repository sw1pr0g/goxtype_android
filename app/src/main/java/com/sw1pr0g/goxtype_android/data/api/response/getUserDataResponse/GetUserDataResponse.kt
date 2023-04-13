package com.sw1pr0g.goxtype_android.data.api.response.getUserDataResponse

import com.google.gson.annotations.SerializedName

data class GetUserDataResponse (
    @SerializedName("data")
    var data: DataObject
)