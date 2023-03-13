package com.sw1pr0g.goxtype_android

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class LogInFragment: Fragment() {

    interface Callbacks {
        fun showFragment(fragment: Fragment)
    }

    private var callbacks: Callbacks? = null
    //private lateinit var logInButton: Button
    private lateinit var signUpTextView: TextView
    private lateinit var signUpImageButton: ImageButton

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
        signUpImageButton = view.findViewById(R.id.sign_up_image_button)

        signUpTextView.setOnClickListener { showSignUpFragment() }
        signUpImageButton.setOnClickListener { showSignUpFragment() }

        return view
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    private fun showSignUpFragment() = callbacks?.showFragment(SignUpFragment())

}