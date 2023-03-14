package com.sw1pr0g.goxtype_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment

class AuthActivity : AppCompatActivity(), AuthLogInFragment.Callbacks, AuthSignUpFragment.Callbacks {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        showFragment(AuthLogInFragment(), getColor(R.color.background), true, firstShowing = true)
    }

    override fun showFragment(fragment: Fragment,
                              statusBarColor: Int,
                              statusBarDarkText: Boolean,
                              firstShowing: Boolean) {

        val supportFragmentManager = supportFragmentManager.beginTransaction().setCustomAnimations(
            R.anim.slide_in,
            R.anim.fade_out,
            R.anim.fade_out,
            R.anim.slide_out,
        ).replace(R.id.auth_fragment_container, fragment)

        if (!firstShowing) supportFragmentManager.addToBackStack(null)

        window.statusBarColor = statusBarColor
        window.setStatusBarDarkText(statusBarDarkText)

        supportFragmentManager.commit()
    }

    private fun Window.setStatusBarDarkText(statusBarDarkText: Boolean) {
        WindowCompat.getInsetsController(this, decorView)
            .isAppearanceLightStatusBars = statusBarDarkText
    }

}