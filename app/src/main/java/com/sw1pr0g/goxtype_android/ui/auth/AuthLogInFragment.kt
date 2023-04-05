package com.sw1pr0g.goxtype_android.ui.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import com.sw1pr0g.goxtype_android.R
import com.sw1pr0g.goxtype_android.data.api.ApiInterface
import com.sw1pr0g.goxtype_android.data.api.LogInBody
import com.sw1pr0g.goxtype_android.data.api.RetrofitInstance
import com.sw1pr0g.goxtype_android.databinding.FragmentAuthLogInBinding
import com.sw1pr0g.goxtype_android.domain.DataValidation
import com.sw1pr0g.goxtype_android.domain.UserAuthAction
import com.sw1pr0g.goxtype_android.ui.main.MainActivity
import kotlinx.coroutines.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AuthLogInFragment: Fragment() {

    private lateinit var binding: FragmentAuthLogInBinding

    interface Callbacks {
        fun showFragment(fragment: Fragment,
                         firstShowing: Boolean)
    }

    private var callbacks: Callbacks? = null
    private var validateChecks: Boolean = false

    private lateinit var dialogAuthLoading: DialogAuthLoading
    private val dataValidation = DataValidation()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthLogInBinding.inflate(inflater, container, false)

        dialogAuthLoading = DialogAuthLoading(requireActivity())
        val userAuthAction = UserAuthAction()

        binding.logInButton.setOnClickListener {

            validateLogInData()
            if (validateChecks) {
                dialogAuthLoading.startLoadingDialog()

                Thread(
                    Runnable {

                        val userAuthResult = userAuthAction.logIn(
                            binding.logInEmailEditText.text.toString(),
                            binding.logInPasswordEditText.text.toString()
                        )

                        when (userAuthResult) {

                            true -> {
                                val intent = Intent(activity, MainActivity::class.java)
                                startActivity(intent)
                                activity?.finish()
                            }
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

        binding.logInEmailEditText.addTextChangedListener { checkOffMistakes() }
        binding.logInPasswordEditText.addTextChangedListener { checkOffMistakes() }

        binding.goSignUpButton.setOnClickListener {
            callbacks?.showFragment(
                AuthSignUpFragment(),
                false)
        }

        return view
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }



    private fun validateLogInData() {
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
    }
}