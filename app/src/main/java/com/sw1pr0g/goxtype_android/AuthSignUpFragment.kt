package com.sw1pr0g.goxtype_android

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment

class SignUpFragment: Fragment() {

    interface Callbacks {
        fun showFragment(fragment: Fragment,
                         statusBarColor: Int,
                         statusBarDarkText: Boolean)
    }

    private var callbacks: Callbacks? = null

    private lateinit var logInTextView: TextView
    private lateinit var logInImageView: ImageView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)

        logInTextView = view.findViewById(R.id.log_in_text_view)
        logInImageView = view.findViewById(R.id.log_in_image_view)

        logInTextView.setOnClickListener { showLogInFragment() }
        logInImageView.setOnClickListener { showLogInFragment() }

        return view
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    private fun showLogInFragment() = callbacks?.showFragment(LogInFragment(), getColor(requireActivity(), R.color.background), true)

}