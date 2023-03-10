package com.sw1pr0g.goxtype_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.auth_fragment_container)

        if (currentFragment == null) {
            val fragment = LogInFragment()
            supportFragmentManager.beginTransaction().add(R.id.auth_fragment_container, fragment).commit()
        }

    }

}