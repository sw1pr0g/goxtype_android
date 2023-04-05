package com.sw1pr0g.goxtype_android.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sw1pr0g.goxtype_android.R
import com.sw1pr0g.goxtype_android.ui.Component

class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val component = Component(this, supportFragmentManager, true)
        component.showFragment(AuthLogInFragment())
    }

}