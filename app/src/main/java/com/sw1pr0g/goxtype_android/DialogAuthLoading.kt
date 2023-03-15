package com.sw1pr0g.goxtype_android

import android.app.Activity
import android.app.AlertDialog

class DialogAuthLoading internal constructor(var activity: Activity) {
    private lateinit var dialog: AlertDialog
    fun startLoadingDialog() {
        val builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        builder.setView(inflater.inflate(R.layout.dialog_auth_loading, null))
        builder.setCancelable(true)
        dialog = builder.create()
        dialog.show()
    }

    fun dismissDialog() {
        dialog.dismiss()
    }
}