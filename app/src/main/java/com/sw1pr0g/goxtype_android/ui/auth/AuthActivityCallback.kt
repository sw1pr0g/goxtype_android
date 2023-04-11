package com.sw1pr0g.goxtype_android.ui.auth

import com.sw1pr0g.goxtype_android.data.api.response.LogInResponse

interface AuthActivityCallback {

    fun processLogIn(data: LogInResponse?)

}