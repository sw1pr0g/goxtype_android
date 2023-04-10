package com.sw1pr0g.goxtype_android.data.api


data class UserBody(val email: String,
                    val password: String,
                    val permissionLevel: Int = 1)
