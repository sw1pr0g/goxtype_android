package com.sw1pr0g.goxtype_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class AuthActivity : AppCompatActivity(), LogInFragment.Callbacks {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.auth_fragment_container)

        if (currentFragment == null) {
            val fragment = LogInFragment()
            supportFragmentManager.beginTransaction().add(R.id.auth_fragment_container, fragment).commit()
        }

    }

    fun onLogInSelected() {
        val fragment = LogInFragment()
        supportFragmentManager.beginTransaction().replace(R.id.auth_fragment_container, fragment)
    }

    override fun onSignUpSelected() {
        val fragment = SignUpFragment()
        supportFragmentManager.beginTransaction().replace(R.id.auth_fragment_container, fragment).
            addToBackStack(null).commit()
    }

}