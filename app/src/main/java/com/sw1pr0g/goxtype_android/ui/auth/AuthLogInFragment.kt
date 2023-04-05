package com.sw1pr0g.goxtype_android.ui.auth

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sw1pr0g.goxtype_android.databinding.FragmentAuthLogInBinding
import com.sw1pr0g.goxtype_android.domain.DataValidation
import com.sw1pr0g.goxtype_android.domain.UserAuthAction
import com.sw1pr0g.goxtype_android.ui.Component
import com.sw1pr0g.goxtype_android.ui.main.MainActivity
import kotlinx.coroutines.*


class AuthLogInFragment: Fragment() {
    private var _binding: FragmentAuthLogInBinding? = null
    private val binding get() = _binding!!

    interface Callbacks {
        fun showFragment(fragment: Fragment)
    }

    private var callbacks: Callbacks? = null
    private var validateChecks: Boolean = false

    private lateinit var dialogAuthLoading: DialogAuthLoading
    private val dataValidation = DataValidation()
    private val component = Component(activity)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAuthLogInBinding.inflate(inflater, container, false)
        val view = binding.root

        dialogAuthLoading = DialogAuthLoading(requireActivity())
        val userAuthAction = UserAuthAction()

        binding.logInButton.setOnClickListener {

            //validateLogInData()
            if (validateChecks) {
                dialogAuthLoading.startLoadingDialog()

                Thread(
                    Runnable {

                        val userAuthResult = userAuthAction.logIn(
                            binding.logInEmailEditText.text.toString(),
                            binding.logInPasswordEditText.text.toString()
                        )

                        when (userAuthResult) {

                            true -> component.newActivity(MainActivity::class.java)
                            else -> {
                                dataValidation.auth(
                                    binding.logInEmailTextLayout,
                                    binding.logInPasswordTextLayout
                                )
                            }

                        }
                    }
                ).start()
            }

        }

        /*binding.logInEmailEditText.addTextChangedListener { checkOffMistakes() }
        binding.logInPasswordEditText.addTextChangedListener { checkOffMistakes() }*/

        binding.goSignUpButton.setOnClickListener {
            callbacks?.showFragment(
                AuthSignUpFragment())
        }

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

    /*private fun validateLogInData() {
        if (logInEmailEditText.text.isEmpty() && logInPasswordEditText.text.isEmpty()) {
            logInEmailTextLayout.isErrorEnabled = true
            logInEmailTextLayout.error = "Email is required"
            logInPasswordTextLayout.isErrorEnabled = true
            logInPasswordTextLayout.error = "Password is required"
            validateChecks = false
        } else if (logInEmailEditText.text.isEmpty()) {
            logInEmailTextLayout.isErrorEnabled = true
            logInEmailTextLayout.error = "Email is required"
            validateChecks = false
        } else if (logInPasswordEditText.text.isEmpty()) {
            logInPasswordTextLayout.isErrorEnabled = true
            logInPasswordTextLayout.error = "Password is required"
            validateChecks = false
        }
    }

    private fun checkOffMistakes() {
        if (logInEmailEditText.text.isNotEmpty()) {
            logInEmailTextLayout.isErrorEnabled = false
            validateChecks = true
        }
        if (logInPasswordEditText.text.isNotEmpty()) {
            logInPasswordTextLayout.isErrorEnabled = false
            validateChecks = true
        }
    }*/
}