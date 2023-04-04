package com.sw1pr0g.goxtype_android.ui.auth

import android.app.Activity
import android.app.AlertDialog
import com.sw1pr0g.goxtype_android.R

class DialogAuthLoading internal constructor(var activity: Activity) {
    private lateinit var dialog: AlertDialog
    fun startLoadingDialog() {
        val builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        builder.setView(inflater.inflate(R.layout.dialog_auth_loading, null))
        builder.setCancelable(true)
        dialog = builder.create()
        dialog.show()
        dialog.window?.setLayout(500, 550)
    }

    fun dismissDialog() {
        dialog.dismiss()
    }
}