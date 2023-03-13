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

        val currentFragment = supportFragmentManager.findFragmentById(R.id.auth_fragment_container)

        if (currentFragment == null)
            supportFragmentManager.beginTransaction()
                .replace(R.id.auth_fragment_container, AuthLogInFragment()).commit()

            //showFragment(AuthLogInFragment(), getColor(R.color.background), true)

    }

    override fun showFragment(fragment: Fragment,
                              statusBarColor: Int, statusBarDarkText: Boolean) {
        supportFragmentManager.beginTransaction().setCustomAnimations(
            R.anim.slide_in,
            R.anim.fade_out,
            R.anim.fade_out,
            R.anim.slide_out,
        ).replace(R.id.auth_fragment_container, fragment).addToBackStack(null).commit()

        window.statusBarColor = statusBarColor
        window.setStatusBarDarkText(statusBarDarkText)
    }

    private fun Window.setStatusBarDarkText(statusBarDarkText: Boolean) {
        WindowCompat.getInsetsController(this, decorView)
            .isAppearanceLightStatusBars = statusBarDarkText
    }

}