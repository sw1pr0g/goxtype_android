package com.sw1pr0g.goxtype_android.ui

import android.app.Activity
import android.content.Context
import android.content.Intent

class Component(private val context: Context?) {
    fun newActivity(activity: Class<*>) {
        val intent = Intent(context, activity)
        context?.startActivity(intent)

        // If activity opened - return to last activity impossible
        context as Activity
        context.finish()
    }
}