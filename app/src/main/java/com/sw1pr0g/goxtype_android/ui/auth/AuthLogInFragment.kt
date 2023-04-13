package com.sw1pr0g.goxtype_android.ui.auth

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sw1pr0g.goxtype_android.data.api.response.BaseResponse
import com.sw1pr0g.goxtype_android.databinding.FragmentAuthLogInBinding
import com.sw1pr0g.goxtype_android.domain.validation.LogInValidation
import com.sw1pr0g.goxtype_android.ui.ShowFragmentCallback
import com.sw1pr0g.goxtype_android.ui.Component
import com.sw1pr0g.goxtype_android.ui.main.MainActivity
import com.sw1pr0g.goxtype_android.ui.viewmodel.AuthViewModel
import com.sw1pr0g.goxtype_android.ui.viewmodel.GetUserDataViewModel
import com.sw1pr0g.goxtype_android.utils.SessionManager


class AuthLogInFragment: Fragment() {
    private var _binding: FragmentAuthLogInBinding? = null
    private val binding get() = _binding!!
    private val authViewModel by viewModels<AuthViewModel>()
    private val getUserDataViewModel by viewModels<GetUserDataViewModel>()

    private var showFragmentCallback: ShowFragmentCallback? = null
    private var authActivityCallback: AuthActivityCallback? = null

    private lateinit var component: Component
    private lateinit var logInValidation: LogInValidation
    private lateinit var dialogAuthLoading: DialogAuthLoading

    override fun onAttach(context: Context) {
        super.onAttach(context)
        showFragmentCallback = context as ShowFragmentCallback?
        authActivityCallback = context as AuthActivityCallback?
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthLogInBinding.inflate(inflater, container, false)
        val view = binding.root

        logInValidation = LogInValidation(binding.logInEmailTextLayout, binding.logInPasswordTextLayout)
        dialogAuthLoading = DialogAuthLoading(requireActivity())
        component = Component(requireContext())

        authViewModel.authResult.observe(requireActivity()) { itAuth ->
            when(itAuth) {
                is BaseResponse.Loading -> {
                    showLoading()
                }
                is BaseResponse.Success -> {
                    authActivityCallback?.processAuth(itAuth.data)
                    stopLoading()
                    getUserData()
                }
                is BaseResponse.Error -> {
                    authActivityCallback?.showErrorSnackBar(itAuth.msg)
                    logInValidation.fieldsIncorrect()
                    stopLoading()
                }
                else -> {
                    stopLoading()
                }
            }
        }

        getUserDataViewModel.getUserDataResult.observe(requireActivity()) { itGetUserData ->

            when (itGetUserData) {

                is BaseResponse.Loading -> {
                    showLoading()
                }
                is BaseResponse.Success -> {
                    authActivityCallback?.processGetUserData(itGetUserData.data)
                    stopLoading()
                }
                is BaseResponse.Error -> {
                    authActivityCallback?.showErrorSnackBar(itGetUserData.msg)
                    stopLoading()
                }
                else -> {
                    stopLoading()
                }

            }

        }

        binding.logInButton.setOnClickListener {
            doLogIn()

        }

        binding.logInEmailEditText.addTextChangedListener { logInValidation.offFieldsMistakes() }
        binding.logInPasswordEditText.addTextChangedListener { logInValidation.offFieldsMistakes() }

        binding.goSignUpButton.setOnClickListener {
            showFragmentCallback?.showFragment(
                AuthSignUpFragment(), false)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDetach() {
        super.onDetach()
        showFragmentCallback = null
        authActivityCallback = null
    }

    private fun doLogIn() {
        if (logInValidation.checkEmptyFields()) {
            val email = binding.logInEmailEditText.text.toString()
            val pwd = binding.logInPasswordEditText.text.toString()
            authViewModel.logInAction = true
            authViewModel.authUser(email = email, pwd = pwd)
        }
    }

    private fun getUserData() {
        getUserDataViewModel.getUserData(
            token = SessionManager.getToken(requireContext()).toString(),
            userId = SessionManager.getIdFromToken(requireContext())
        )
    }

    private fun showLoading() {
        dialogAuthLoading.startLoadingDialog()
    }

    private fun stopLoading() {
        dialogAuthLoading.dismissDialog()
    }

}