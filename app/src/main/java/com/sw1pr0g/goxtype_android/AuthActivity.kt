package com.sw1pr0g.goxtype_android

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.fragment.app.Fragment

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

    override fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.auth_fragment_container, fragment).addToBackStack(null).commit()
    }

    //.setCustomAnimations(R.anim.slide_in_left, R.anim.stay)

}