package com.sw1pr0g.goxtype_android

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class LogInFragment: Fragment() {

    interface Callbacks {
        fun onSignUpSelected()
    }

    private var callbacks: Callbacks? = null
    private lateinit var buttonLogIn: Button
    private lateinit var signUpText: TextView
    private lateinit var signUpPlus: ImageView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_log_in, container, false)
        buttonLogIn = view.findViewById(R.id.log_in_button) as Button

        buttonLogIn.setOnClickListener {
            callbacks?.onSignUpSelected()
        }

        return view
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

}