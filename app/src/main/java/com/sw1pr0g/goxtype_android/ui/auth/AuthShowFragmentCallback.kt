package com.sw1pr0g.goxtype_android.ui.auth

import androidx.fragment.app.Fragment

interface AuthShowFragmentCallback {
    fun showFragment(fragment: Fragment, firstShowing: Boolean)
}