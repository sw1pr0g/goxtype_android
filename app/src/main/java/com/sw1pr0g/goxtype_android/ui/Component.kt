package com.sw1pr0g.goxtype_android.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.sw1pr0g.goxtype_android.R
import com.sw1pr0g.goxtype_android.ui.auth.AuthLogInFragment
import com.sw1pr0g.goxtype_android.ui.auth.AuthSignUpFragment
import com.sw1pr0g.goxtype_android.ui.main.MainActivity

class Component(private val context: Context?) {
    fun newActivity(activity: Class<*>, toMain: Boolean = false) {
        val intent = Intent(context, activity)

        if (toMain) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        }

        context?.startActivity(intent)

        // If activity opened - return to last activity impossible
        context as Activity
        context.finish()
    }
}