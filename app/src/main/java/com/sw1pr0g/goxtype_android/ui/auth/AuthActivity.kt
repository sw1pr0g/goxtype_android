package com.sw1pr0g.goxtype_android.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.sw1pr0g.goxtype_android.R

class AuthActivity : AppCompatActivity(), AuthLogInFragment.Callbacks,
    AuthSignUpFragment.Callbacks {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        showFragment(AuthLogInFragment(), true)
    }

    override fun showFragment(fragment: Fragment,
                              firstShowing: Boolean) {


    }

}