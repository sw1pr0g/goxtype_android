package com.sw1pr0g.goxtype_android.ui.auth

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import com.sw1pr0g.goxtype_android.R
import com.sw1pr0g.goxtype_android.databinding.FragmentAuthSignUpBinding
import com.sw1pr0g.goxtype_android.ui.Component
import com.sw1pr0g.goxtype_android.ui.ShowFragmentCallback

class AuthSignUpFragment: Fragment() {
    private var _binding: FragmentAuthSignUpBinding? = null
    private val binding get() = _binding!!

    private var callbacks: ShowFragmentCallback? = null
    private var validateChecks: Boolean = false

    private lateinit var dialogAuthLoading: DialogAuthLoading
    private lateinit var component: Component

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as ShowFragmentCallback?
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAuthSignUpBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.goLogInButton.setOnClickListener { callbacks?.showFragment(AuthLogInFragment(), false) }

        binding.signUpButton.setOnClickListener {

        }

        binding.signUpEmailEditText.addTextChangedListener { checkOffMistakes() }
        binding.signUpPasswordEditText.addTextChangedListener { checkOffMistakes() }
        binding.signUpRepeatPasswordEditText.addTextChangedListener { checkOffMistakes() }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    private fun validateSignUpData() {



    }

    private fun checkOffMistakes() {
        if (signUpEmailEditText.text.isNotEmpty()) {
            signUpEmailTextLayout.isErrorEnabled = false
            validateChecks = true
        }
        if (signUpPasswordEditText.text.isNotEmpty()) {
            signUpPasswordTextLayout.isErrorEnabled = false
            validateChecks = true
        }
        if (signUpRepeatPasswordEditText.text.isNotEmpty()) {
            signUpRepeatPasswordTextLayout.isErrorEnabled = false
            validateChecks = true
        }
    }

    private fun EditText.isEmailValid(): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(this.text.toString()).matches()
    }
}