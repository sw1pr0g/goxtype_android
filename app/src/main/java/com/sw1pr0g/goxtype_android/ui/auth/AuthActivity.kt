package com.sw1pr0g.goxtype_android.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.sw1pr0g.goxtype_android.R
import com.sw1pr0g.goxtype_android.data.api.response.AuthResponse
import com.sw1pr0g.goxtype_android.databinding.ActivityAuthBinding
import com.sw1pr0g.goxtype_android.ui.Component
import com.sw1pr0g.goxtype_android.ui.ShowFragmentCallback
import com.sw1pr0g.goxtype_android.ui.main.MainActivity
import com.sw1pr0g.goxtype_android.utils.SessionManager

class AuthActivity : AppCompatActivity(), AuthActivityCallback, ShowFragmentCallback {
    private lateinit var binding: ActivityAuthBinding

    private lateinit var component: Component

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        component = Component(this)

        showFragment(AuthLogInFragment(), true)
    }

    override fun processLogIn(data: AuthResponse?) {
        if (!data?.token.isNullOrEmpty()) {
            data?.token?.let {
                SessionManager.saveAuthToken(this, it) }
            component.newActivity(MainActivity::class.java, true)
        }
    }

    fun processSignUp(data: AuthResponse?) {

    }

    override fun showErrorSnackBar(message: String?) {
        if (message != null) {
            Snackbar.make(binding.authFragmentContainer, message, Snackbar.LENGTH_SHORT)
                .setBackgroundTint(ContextCompat.getColor(applicationContext, R.color.colorPrimary))
                .show()
        }
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