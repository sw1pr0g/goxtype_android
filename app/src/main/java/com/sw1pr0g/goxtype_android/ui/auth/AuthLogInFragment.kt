package com.sw1pr0g.goxtype_android.ui.auth

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NO_HISTORY
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.sw1pr0g.goxtype_android.R
import com.sw1pr0g.goxtype_android.data.api.response.BaseResponse
import com.sw1pr0g.goxtype_android.data.api.response.LoginResponse
import com.sw1pr0g.goxtype_android.databinding.FragmentAuthLogInBinding
import com.sw1pr0g.goxtype_android.domain.DataValidation
import com.sw1pr0g.goxtype_android.domain.UserAuthAction
import com.sw1pr0g.goxtype_android.ui.Component
import com.sw1pr0g.goxtype_android.ui.main.MainActivity
import com.sw1pr0g.goxtype_android.utils.SessionManager
import com.sw1pr0g.goxtype_android.viewmodel.LoginViewModel
import kotlinx.coroutines.*


class AuthLogInFragment: Fragment() {
    private var _binding: FragmentAuthLogInBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<LoginViewModel>()

    private var callbacks: AuthShowFragmentCallback? = null

    private lateinit var dataValidation: DataValidation
    private lateinit var userAuthAction: UserAuthAction
    private lateinit var dialogAuthLoading: DialogAuthLoading

    private val component = Component(activity)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as AuthShowFragmentCallback?
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthLogInBinding.inflate(inflater, container, false)
        val view = binding.root

        val token = SessionManager.getToken(requireActivity())
        if (token.isNullOrBlank()) {
            navigateToHome()
        }

        viewModel.loginResult.observe(requireActivity()) {
            when(it) {
                is BaseResponse.Loading -> {
                    showLoading()
                }
                is BaseResponse.Success -> {
                    stopLoading()
                    processLogin(it.data)
                }
                is BaseResponse.Error -> {
                    processError(it.msg)
                }
                else -> {
                    stopLoading()
                }
            }
        }

        dataValidation = DataValidation(binding.logInEmailTextLayout, binding.logInPasswordTextLayout)

        userAuthAction = UserAuthAction()

        dialogAuthLoading = DialogAuthLoading(requireActivity())

        binding.logInButton.setOnClickListener { doLogin() }

        binding.logInEmailEditText.addTextChangedListener { dataValidation.authOffMistakes(binding.logInEmailTextLayout, binding.logInPasswordTextLayout) }
        binding.logInPasswordEditText.addTextChangedListener { dataValidation.authOffMistakes(binding.logInEmailTextLayout, binding.logInPasswordTextLayout) }

        binding.goSignUpButton.setOnClickListener {
            callbacks?.showFragment(
                AuthSignUpFragment(), false)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        ViewModel.
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    private fun navigateToHome() {
        val intent = Intent(requireActivity(), MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(FLAG_ACTIVITY_NO_HISTORY)
        startActivity(intent)
    }

    private fun doLogin() {
        val email = binding.logInEmailEditText.text.toString()
        val pwd = binding.logInPasswordEditText.text.toString()
        viewModel.loginUser(email = email, pwd = pwd)
    }

    fun showLoading() {
        dialogAuthLoading.startLoadingDialog()
    }

    fun stopLoading() {
        dialogAuthLoading.dismissDialog()
    }

    fun processLogin(data: LoginResponse?) {
        showToast("Success:" + data?.message)
        if (!data?.token.isNullOrEmpty()) {
            data?.token?.let {
                SessionManager.saveAuthToken(requireActivity(), it) }
            navigateToHome()
        }
    }

    fun processError(msg: String?) {
        showToast("Error:" + msg)
    }

    fun showToast(msg: String) {
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_SHORT).show()
    }

    private fun logIn() {

        /*val mainLooper = Looper.getMainLooper()

        var userLogInResponse = 0*/

        Thread(Runnable {

            userAuthAction.logIn(binding.logInEmailEditText.text.toString(),
                binding.logInPasswordEditText.text.toString())

            /*if(dataValidation.authLogIn()) {


                Handler(mainLooper).post {
                    dataValidation.authFieldsUpdate(userLogInResponse, requireContext())
                }
            }*/

            Thread.sleep(1000)

        }).start()

        val i = 0

        /*var userAuthResult = 0

        Thread(Runnable {
            dialogAuthLoading.startLoadingDialog()

            if (dataValidation.authLogIn()) {
                userAuthResult = userAuthAction.logIn(
                    binding.logInEmailEditText.text.toString(),
                    binding.logInPasswordEditText.text.toString()
                )
            }

            Thread.sleep(1000)
            dialogAuthLoading.dismissDialog()
        })

        if (userAuthResult != 0) {
            when (userAuthResult) {
                201 -> component.newActivity(MainActivity::class.java)
                404 -> {
                    Looper.prepare()
                    dataValidation.authDataIncorrect()
                    Toast.makeText(
                        activity,
                        R.string.log_in_server_error,
                        Toast.LENGTH_SHORT
                    ).show()
                    Looper.loop()
                }
                else -> {
                    Looper.prepare()
                    dataValidation.authDataIncorrect()
                    Looper.loop()
                }
            }
        }*/
    }

}