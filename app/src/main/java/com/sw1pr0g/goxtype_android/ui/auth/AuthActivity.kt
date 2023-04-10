package com.sw1pr0g.goxtype_android.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.sw1pr0g.goxtype_android.R
import com.sw1pr0g.goxtype_android.ui.ShowFragmentCallback

class AuthActivity : AppCompatActivity(), ShowFragmentCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        showFragment(AuthLogInFragment(), true)
    }

    override fun showFragment(fragment: Fragment, firstShowing: Boolean) {
        val fragmentTransaction = supportFragmentManager.beginTransaction().setCustomAnimations(
            R.anim.slide_in,
            R.anim.fade_out,
            R.anim.fade_out,
            R.anim.slide_out,
        ).replace(R.id.auth_fragment_container, fragment)

        if (!firstShowing) fragmentTransaction.addToBackStack(null)

        fragmentTransaction.commit()
    }

}