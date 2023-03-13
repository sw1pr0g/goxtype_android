package com.sw1pr0g.goxtype_android

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class LogInFragment: Fragment() {

    interface Callbacks {
        fun showSignUpFragment()
    }

    private var callbacks: Callbacks? = null
    //private lateinit var logInButton: Button
    private lateinit var signUpTextView: TextView
    private lateinit var signUpImageView: ImageView

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

        signUpTextView = view.findViewById(R.id.sign_up_text_view)
        signUpImageView = view.findViewById(R.id.sign_up_image_view)

        signUpTextView.setOnClickListener { showSignUpFragment() }
        signUpImageView.setOnClickListener { showSignUpFragment() }

        return view
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    private fun showSignUpFragment() = callbacks?.showSignUpFragment()

}