package com.sw1pr0g.goxtype_android.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.sw1pr0g.goxtype_android.R
import com.sw1pr0g.goxtype_android.ui.auth.AuthLogInFragment

class Component(private val context: Context?): AuthLogInFragment.Callbacks {
    fun newActivity(activity: Class<*>) {
        val intent = Intent(context, activity)
        context?.startActivity(intent)

        // If activity opened - return to last activity impossible
        context as Activity
        context.finish()
    }


    override fun showFragment(fragment: Fragment, firstShowing: Boolean) {
        val supportFragmentManager: FragmentManager = fragment.requireActivity().supportFragmentManager

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