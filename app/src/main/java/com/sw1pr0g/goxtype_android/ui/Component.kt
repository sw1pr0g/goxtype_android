package com.sw1pr0g.goxtype_android.ui

import android.content.Context
import android.content.Intent

class Component(private val context: Context) {

    fun newActivity(activity: Class<*>) {
        val intent = Intent(context, activity)
        context.startActivity(intent)
    }
}