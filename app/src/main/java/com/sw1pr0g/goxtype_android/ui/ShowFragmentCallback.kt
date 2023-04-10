package com.sw1pr0g.goxtype_android.ui

import androidx.fragment.app.Fragment

interface ShowFragmentCallback {
    fun showFragment(fragment: Fragment, firstShowing: Boolean)
}