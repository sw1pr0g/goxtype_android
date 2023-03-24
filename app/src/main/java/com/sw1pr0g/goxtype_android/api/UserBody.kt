package com.sw1pr0g.goxtype_android.api


data class UserBody(val email: String,
                    val password: String,
                    val permissionLevel: Int = 1)
