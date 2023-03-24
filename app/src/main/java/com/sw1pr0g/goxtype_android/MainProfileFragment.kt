package com.sw1pr0g.goxtype_android

import android.content.Context
import android.os.Bundle
import android.telecom.Call
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment

class MainProfileFragment: Fragment() {

    /*interface Callbacks {

        fun showFragment(fragment: Fragment, firstShowing: Boolean)

    }*/

    /*private var callbacks: Callbacks? = null*/

    /*override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main_profile, container, false)

        return view
    }

    /*override fun onDetach() {
        super.onDetach()
        callbacks = null
    }*/

}