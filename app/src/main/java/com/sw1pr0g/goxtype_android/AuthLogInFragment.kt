package com.sw1pr0g.goxtype_android

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment

class AuthLogInFragment: Fragment() {

    interface Callbacks {
        fun showFragment(fragment: Fragment,
                         statusBarColor: Int,
                         statusBarDarkText: Boolean,
                         firstShowing: Boolean)
    }

    private var callbacks: Callbacks? = null
    private lateinit var logInButton: Button
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
        val view = inflater.inflate(R.layout.fragment_auth_log_in, container, false)

        logInButton = view.findViewById(R.id.log_in_button)
        signUpTextView = view.findViewById(R.id.sign_up_text_view)
        signUpImageButton = view.findViewById(R.id.sign_up_image_button)

        logInButton.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
        signUpTextView.setOnClickListener { showSignUpFragment() }
        signUpImageButton.setOnClickListener { showSignUpFragment() }

        return view
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    private fun showSignUpFragment() = callbacks?.showFragment(AuthSignUpFragment(),
        getColor(requireActivity(), R.color.button_text), false,
        firstShowing = false)

}